package com.geeksforless.tfedorenko.service;

import com.geeksforless.tfedorenko.entity.Manufacturer;
import com.geeksforless.tfedorenko.entity.Souvenir;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ManufacturerService {

    void createManufacturer(Manufacturer manufacturer);

    void updateManufacturer(Manufacturer manufacturer);

    void deleteManufacturer(Long id);

    Optional<Manufacturer> findManufacturerById(Long id);

    List<Manufacturer> findAllManufacturer();

    List<Manufacturer> findByLessPrice(BigDecimal price);

    Map<Manufacturer, List<Souvenir>> findAllManufacturerAndSouvenir();

    List<Manufacturer> findBySouvenirAndYear(String souvenirName, int year);
}
