package com.geeksforless.tfedorenko.dao.impl;

import com.geeksforless.tfedorenko.dao.ManufacturerDAO;
import com.geeksforless.tfedorenko.dao.SouvenirDAO;
import com.geeksforless.tfedorenko.entity.Manufacturer;
import com.geeksforless.tfedorenko.entity.Souvenir;
import com.geeksforless.tfedorenko.io.souvenirIO.SouvenirIO;
import com.geeksforless.tfedorenko.utils.generatorId.factory.GeneratorIdFactory;
import com.geeksforless.tfedorenko.utils.generatorId.factory.impl.SouvenirGeneratorIdFactory;
import com.geeksforless.tfedorenko.utils.init.CreateFileAndInit;

import java.util.*;
import java.util.stream.Collectors;

public class SouvenirDAOImpl implements SouvenirDAO {

    private final ManufacturerDAO manufacturerDAO = new ManufacturerDAOImpl();
    private final SouvenirIO io = SouvenirIO.getInstance();
    private final String filePath = "souvenirs.json";
    private final GeneratorIdFactory<Souvenir> idGeneratorFactory = new SouvenirGeneratorIdFactory();

    public SouvenirDAOImpl() {
        CreateFileAndInit.createInitSouvenir(filePath);
    }

    @Override
    public void create(Souvenir souvenir) {
        souvenir.setId(idGeneratorFactory.createGenerator().generateId(filePath));
        io.updateSouvenirsInFile(souvenirs -> souvenirs.add(souvenir));
        manufacturerDAO.addSouvenirToList(souvenir, souvenir.getManufacturerId());
    }

    @Override
    public void update(Souvenir souvenir) {
        io.updateSouvenirsInFile(souvenirs -> {
            for (int i = 0; i < souvenirs.size(); i++) {
                if (souvenirs.get(i).getId().equals(souvenir.getId())) {
                    souvenirs.set(i, souvenir);
                    break;
                }
            }
        });
        manufacturerDAO.updateSouvenirInList(souvenir);
    }

    @Override
    public void delete(Long id) {
        io.updateSouvenirsInFile(souvenirs -> souvenirs.removeIf(souvenir -> souvenir.getId().equals(id)));
        manufacturerDAO.deleteSouvenirFromList(id);
    }

    @Override
    public Optional<Souvenir> findById(Long id) {
        return io.readSouvenirsFromFile()
                .stream()
                .filter(souvenir -> souvenir.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Souvenir> findAll() {
        return io.readSouvenirsFromFile();
    }

    @Override
    public List<Souvenir> findByManufacturer(Long manufacturerId) {
        return io.readSouvenirsFromFile()
                .stream()
                .filter(souvenir -> souvenir.getManufacturerId().equals(manufacturerId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Souvenir> findByCountry(String country) {
        return io.readSouvenirsFromFile()
                .stream()
                .filter(souvenir -> {
                    Optional<Manufacturer> manufacturer = manufacturerDAO.findById(souvenir.getManufacturerId());
                    return manufacturer.isPresent() && manufacturer.get().getCountry().equals(country);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Souvenir> findByYear(int year) {
        return io.readSouvenirsFromFile()
                .stream()
                .filter(souvenir -> souvenir.getDate().getYear() == year)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isSouvenirExist(Souvenir souvenir) {
        List<Souvenir> souvenirs = io.readSouvenirsFromFile();
        for (Souvenir sov : souvenirs) {
            if (sov.getName().equals(souvenir.getName()) && sov.getManufacturerId().equals(souvenir.getManufacturerId())) {
                return true;
            }
        }
        return false;
    }
}

