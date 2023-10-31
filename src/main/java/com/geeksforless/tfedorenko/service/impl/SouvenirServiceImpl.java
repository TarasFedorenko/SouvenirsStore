package com.geeksforless.tfedorenko.service.impl;

import com.geeksforless.tfedorenko.dao.SouvenirDAO;
import com.geeksforless.tfedorenko.dao.impl.SouvenirDAOImpl;
import com.geeksforless.tfedorenko.entity.Souvenir;
import com.geeksforless.tfedorenko.service.SouvenirService;
import com.geeksforless.tfedorenko.utils.colorHelper.ColorsMessage;


import java.util.List;
import java.util.Optional;

public class SouvenirServiceImpl implements SouvenirService {
    private final SouvenirDAO souvenirDAO = new SouvenirDAOImpl();

    @Override
    public void createSouvenir(Souvenir souvenir) {
        if (!isSouvenirExist(souvenir)) {
            souvenirDAO.create(souvenir);
        } else {
            System.out.println(ColorsMessage.RED + "This souvenir already exist");
        }
    }

    @Override
    public void updateSouvenir(Souvenir souvenir) {
        if (!isSouvenirExist(souvenir)) {
            souvenirDAO.update(souvenir);
        } else {
            System.out.println(ColorsMessage.RED + "This souvenir already exist");
        }
    }

    @Override
    public void deleteSouvenir(Long id) {
        souvenirDAO.delete(id);
    }

    @Override
    public Optional<Souvenir> findSouvenirById(Long id) {
        return souvenirDAO.findById(id);
    }

    @Override
    public List<Souvenir> findAllSouvenir() {
        return souvenirDAO.findAll();
    }

    @Override
    public List<Souvenir> findByManufacturer(Long id) {
        return souvenirDAO.findByManufacturer(id);
    }

    @Override
    public List<Souvenir> findByCountry(String country) {
        return souvenirDAO.findByCountry(country);
    }

    @Override
    public List<Souvenir> findByYear(int year) {
        return souvenirDAO.findByYear(year);
    }

    private boolean isSouvenirExist(Souvenir souvenir) {
        return souvenirDAO.isSouvenirExist(souvenir);
    }
}
