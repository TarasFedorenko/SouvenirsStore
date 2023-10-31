package com.geeksforless.tfedorenko.service;

import com.geeksforless.tfedorenko.entity.Souvenir;


import java.util.List;
import java.util.Optional;


public interface SouvenirService {

    void createSouvenir(Souvenir souvenir);

    void updateSouvenir(Souvenir souvenir);

    void deleteSouvenir(Long id);

    Optional<Souvenir> findSouvenirById(Long id);

    List<Souvenir> findAllSouvenir();

    List<Souvenir> findByManufacturer(Long id);

    List<Souvenir> findByCountry(String country);

    List<Souvenir> findByYear(int year);

}
