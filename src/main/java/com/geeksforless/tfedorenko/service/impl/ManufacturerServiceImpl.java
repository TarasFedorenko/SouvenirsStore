package com.geeksforless.tfedorenko.service.impl;

import com.geeksforless.tfedorenko.dao.ManufacturerDAO;
import com.geeksforless.tfedorenko.dao.impl.ManufacturerDAOImpl;
import com.geeksforless.tfedorenko.entity.Manufacturer;
import com.geeksforless.tfedorenko.entity.Souvenir;
import com.geeksforless.tfedorenko.service.ManufacturerService;
import com.geeksforless.tfedorenko.utils.colorHelper.ColorsMessage;

import java.math.BigDecimal;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ManufacturerServiceImpl implements ManufacturerService {
    private final ManufacturerDAO manufacturerDAO = new ManufacturerDAOImpl();

    @Override
    public void createManufacturer(Manufacturer manufacturer) {
        if (!isManufacturerExist(manufacturer)) {
            manufacturerDAO.create(manufacturer);
        } else {
            System.out.println(ColorsMessage.RED + "Manufacturer already exist");
        }
    }

    @Override
    public void updateManufacturer(Manufacturer manufacturer) {
        if (!isManufacturerExist(manufacturer)) {
            manufacturerDAO.update(manufacturer);
        } else {
            System.out.println(ColorsMessage.RED + "Manufacturer already exist");
        }
    }

    @Override
    public void deleteManufacturer(Long id) {
        manufacturerDAO.delete(id);
    }

    @Override
    public Optional<Manufacturer> findManufacturerById(Long id) {
        return manufacturerDAO.findById(id);
    }

    @Override
    public List<Manufacturer> findAllManufacturer() {
        return manufacturerDAO.findAll();
    }

    @Override
    public List<Manufacturer> findByLessPrice(BigDecimal price) {
        return manufacturerDAO.findByLessPrice(price);
    }

    @Override
    public Map<Manufacturer, List<Souvenir>> findAllManufacturerAndSouvenir() {
        return manufacturerDAO.findAllManufacturerAndSouvenir();
    }

    @Override
    public List<Manufacturer> findBySouvenirAndYear(String souvenirName, int year) {
        return manufacturerDAO.findBySouvenirAndYear(souvenirName, year);
    }

    private boolean isManufacturerExist(Manufacturer manufacturer) {
        return manufacturerDAO.isManufacturerExist(manufacturer);
    }
}
