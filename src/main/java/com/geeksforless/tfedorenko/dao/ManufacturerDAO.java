package com.geeksforless.tfedorenko.dao;

import com.geeksforless.tfedorenko.entity.Manufacturer;
import com.geeksforless.tfedorenko.entity.Souvenir;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public interface ManufacturerDAO extends CRUDInterface<Manufacturer> {

    List<Manufacturer> findByLessPrice(BigDecimal price);

    Map<Manufacturer, List<Souvenir>> findAllManufacturerAndSouvenir();

    List<Manufacturer> findBySouvenirAndYear(String souvenirName, int year);

    void addSouvenirToList(Souvenir souvenir, Long id);

    void updateSouvenirInList(Souvenir souvenir);

    void deleteSouvenirFromList(Long id);

    boolean isManufacturerExist(Manufacturer manufacturer);

}
