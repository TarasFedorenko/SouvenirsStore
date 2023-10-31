package com.geeksforless.tfedorenko.dao;

import com.geeksforless.tfedorenko.entity.Souvenir;

import java.util.List;


public interface SouvenirDAO extends CRUDInterface<Souvenir> {

    List<Souvenir> findByManufacturer(Long id);

    List<Souvenir> findByCountry(String country);

    List<Souvenir> findByYear(int year);

    boolean isSouvenirExist(Souvenir souvenir);


}
