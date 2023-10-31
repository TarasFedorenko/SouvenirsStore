package com.geeksforless.tfedorenko.dao.impl;

import com.geeksforless.tfedorenko.dao.ManufacturerDAO;
import com.geeksforless.tfedorenko.data.ManufacturerContainer;
import com.geeksforless.tfedorenko.data.SouvenirContainer;
import com.geeksforless.tfedorenko.entity.Manufacturer;
import com.geeksforless.tfedorenko.entity.Souvenir;
import com.geeksforless.tfedorenko.io.manufacturerIO.ManufacturerIO;
import com.geeksforless.tfedorenko.io.souvenirIO.SouvenirIO;
import com.geeksforless.tfedorenko.utils.generatorId.factory.GeneratorIdFactory;
import com.geeksforless.tfedorenko.utils.generatorId.factory.impl.ManufacturerGeneratorIdFactory;
import com.geeksforless.tfedorenko.utils.init.CreateFileAndInit;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class ManufacturerDAOImpl implements ManufacturerDAO {
    private final String filePath = "manufacturers.json";
    private final ManufacturerIO ioManufacturer = ManufacturerIO.getInstance();
    private final SouvenirIO ioSouvenir = SouvenirIO.getInstance();
    private final GeneratorIdFactory<Manufacturer> idGeneratorFactory = new ManufacturerGeneratorIdFactory();

    public ManufacturerDAOImpl() {
        CreateFileAndInit.createInitManufacturer(filePath);
    }

    @Override
    public void create(Manufacturer manufacturer) {
        manufacturer.setId(idGeneratorFactory.createGenerator().generateId(filePath));
        ioManufacturer.updateManufacturersInFile(manufacturers -> manufacturers.add(manufacturer));
    }

    @Override
    public void update(Manufacturer manufacturer) {
        ioManufacturer.updateManufacturersInFile(manufacturers -> {
            for (int i = 0; i < manufacturers.size(); i++) {
                if (manufacturers.get(i).getId().equals(manufacturer.getId())) {
                    manufacturers.set(i, manufacturer);
                    break;
                }
            }
        });
    }

    @Override
    public void delete(Long id) {
        List<Manufacturer> manufacturerList = ioManufacturer.readManufacturersFromFile();
        manufacturerList.removeIf(manufacturer -> {
            if (manufacturer.getId().equals(id)) {
                List<Long> souvenirIdsToRemove = manufacturer.getSouvenirs()
                        .stream()
                        .map(Souvenir::getId)
                        .collect(Collectors.toList());
                List<Souvenir> souvenirs = ioSouvenir.readSouvenirsFromFile();
                souvenirs.removeIf(souvenir -> souvenirIdsToRemove.contains(souvenir.getId()));
                ioSouvenir.writeSouvenirsToFile(new SouvenirContainer(souvenirs));
                return true;
            }
            return false;
        });
        ioManufacturer.writeManufacturersToFile(new ManufacturerContainer(manufacturerList));
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return ioManufacturer.readManufacturersFromFile()
                .stream()
                .filter(manufacturer -> manufacturer.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Manufacturer> findAll() {
        return ioManufacturer.readManufacturersFromFile();
    }

    @Override
    public List<Manufacturer> findByLessPrice(BigDecimal price) {
        return ioManufacturer.readManufacturersFromFile()
                .stream()
                .filter(manufacturer -> {
                    List<Souvenir> souvenirs = manufacturer.getSouvenirs();
                    BigDecimal maxSouvenirPrice = souvenirs.stream()
                            .map(Souvenir::getPrice)
                            .max(BigDecimal::compareTo)
                            .orElse(BigDecimal.ZERO);
                    return maxSouvenirPrice.compareTo(price) < 0;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Map<Manufacturer, List<Souvenir>> findAllManufacturerAndSouvenir() {
        return ioManufacturer.readManufacturersFromFile()
                .stream()
                .collect(Collectors.toMap(manufacturer -> manufacturer, Manufacturer::getSouvenirs));
    }

    @Override
    public List<Manufacturer> findBySouvenirAndYear(String souvenirName, int year) {
        return ioManufacturer.readManufacturersFromFile()
                .stream()
                .filter(manufacturer ->
                        manufacturer.getSouvenirs().stream()
                                .anyMatch(souvenir ->
                                        souvenir.getName().equals(souvenirName) && souvenir.getDate().getYear() == year
                                )
                )
                .collect(Collectors.toList());
    }

    public void addSouvenirToList(Souvenir souvenir, Long id) {
        List<Manufacturer> manufacturerList = ioManufacturer.readManufacturersFromFile();
        for (Manufacturer manufacturer : manufacturerList) {
            if (manufacturer.getId().equals(id)) {
                manufacturer.getSouvenirs().add(souvenir);
                break;
            }
        }
        ioManufacturer.writeManufacturersToFile(new ManufacturerContainer(manufacturerList));
    }

    public void updateSouvenirInList(Souvenir souvenir) {
        List<Manufacturer> manufacturerList = ioManufacturer.readManufacturersFromFile();
        for (Manufacturer manufacturer : manufacturerList) {
            for (Souvenir existingSouvenir : manufacturer.getSouvenirs()) {
                if (existingSouvenir.getId().equals(souvenir.getId())) {
                    existingSouvenir.setName(souvenir.getName());
                    existingSouvenir.setPrice(souvenir.getPrice());
                    existingSouvenir.setDate(souvenir.getDate());
                    break;
                }
            }
        }
        ioManufacturer.writeManufacturersToFile(new ManufacturerContainer(manufacturerList));
    }

    public void deleteSouvenirFromList(Long id) {
        List<Manufacturer> manufacturerList = ioManufacturer.readManufacturersFromFile();
        for (Manufacturer manufacturer : manufacturerList) {
            manufacturer.getSouvenirs().removeIf(souvenir -> souvenir.getId().equals(id));
        }
        ioManufacturer.writeManufacturersToFile(new ManufacturerContainer(manufacturerList));
    }

    @Override
    public boolean isManufacturerExist(Manufacturer manufacturer) {
        List<Manufacturer> manufacturers = ioManufacturer.readManufacturersFromFile();
        for (Manufacturer man : manufacturers) {
            if (man.getName().equals(manufacturer.getName()) && man.getCountry().equals(manufacturer.getCountry())) {
                return true;
            }
        }
        return false;
    }
}