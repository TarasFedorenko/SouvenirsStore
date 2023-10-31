package com.geeksforless.tfedorenko.service;

import com.geeksforless.tfedorenko.entity.Manufacturer;
import com.geeksforless.tfedorenko.entity.Souvenir;
import com.geeksforless.tfedorenko.service.impl.ManufacturerServiceImpl;
import com.geeksforless.tfedorenko.service.impl.SouvenirServiceImpl;
import org.junit.jupiter.api.*;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServiceTest {
    private static final SouvenirService souvenirService = new SouvenirServiceImpl();

    private static final ManufacturerService manufacturerService = new ManufacturerServiceImpl();

    private static final long SOUVENIRS_SIZE = 10;

    private static final long MANUFACTURER_SIZE = 5;


    @BeforeAll
    public static void setUpManufacturers() {
        Manufacturer manufacturer1 = new Manufacturer("Puma", "Germany");
        manufacturerService.createManufacturer(manufacturer1);
        Manufacturer manufacturer2 = new Manufacturer("Adidas", "Germany");
        manufacturerService.createManufacturer(manufacturer2);
        Manufacturer manufacturer3 = new Manufacturer("SuperDry", "Japan");
        manufacturerService.createManufacturer(manufacturer3);
        Manufacturer manufacturer4 = new Manufacturer("Gucci", "Italy");
        manufacturerService.createManufacturer(manufacturer4);
        Manufacturer manufacturer5 = new Manufacturer("Disney", "USA");
        manufacturerService.createManufacturer(manufacturer5);
    }

    @BeforeAll
    public static void setUpSouvenirs() {
        Souvenir souvenir1 = new Souvenir("Football ball", LocalDate.parse("2000-05-22"), BigDecimal.valueOf(500.00), 1L);
        souvenirService.createSouvenir(souvenir1);
        Souvenir souvenir2 = new Souvenir("Basketball ball", LocalDate.parse("2001-04-21"), BigDecimal.valueOf(400.00), 1L);
        souvenirService.createSouvenir(souvenir2);
        Souvenir souvenir3 = new Souvenir("Football ball", LocalDate.parse("2012-01-02"), BigDecimal.valueOf(550.00), 2L);
        souvenirService.createSouvenir(souvenir3);
        Souvenir souvenir4 = new Souvenir("Brand T-short", LocalDate.parse("2005-05-28"), BigDecimal.valueOf(200.00), 2L);
        souvenirService.createSouvenir(souvenir4);
        Souvenir souvenir5 = new Souvenir("Red hoody", LocalDate.parse("2016-02-12"), BigDecimal.valueOf(750.00), 3L);
        souvenirService.createSouvenir(souvenir5);
        Souvenir souvenir6 = new Souvenir("Blue hat", LocalDate.parse("2021-01-31"), BigDecimal.valueOf(250.00), 3L);
        souvenirService.createSouvenir(souvenir6);
        Souvenir souvenir7 = new Souvenir("White T-short", LocalDate.parse("2000-05-22"), BigDecimal.valueOf(1200.00), 4L);
        souvenirService.createSouvenir(souvenir7);
        Souvenir souvenir8 = new Souvenir("Crystal ball", LocalDate.parse("2000-05-22"), BigDecimal.valueOf(800.00), 4L);
        souvenirService.createSouvenir(souvenir8);
        Souvenir souvenir9 = new Souvenir("Tea cup", LocalDate.parse("2000-05-22"), BigDecimal.valueOf(150.00), 5L);
        souvenirService.createSouvenir(souvenir9);
        Souvenir souvenir10 = new Souvenir("Coffee cup", LocalDate.parse("2000-05-22"), BigDecimal.valueOf(170.00), 5L);
        souvenirService.createSouvenir(souvenir10);
    }

    @Test
    @Order(1)
    public void checkSizeManufacturer() {
        Assertions.assertEquals(manufacturerService.findAllManufacturer().size(), MANUFACTURER_SIZE);
    }

    @Test
    @Order(2)
    public void checkSizeSouvenir() {
        Assertions.assertEquals(souvenirService.findAllSouvenir().size(), SOUVENIRS_SIZE);
    }

    @Test
    @Order(3)
    public void createManufacturerTest() {
        Manufacturer manufacturer = new Manufacturer("DC", "USA");
        manufacturerService.createManufacturer(manufacturer);
        Assertions.assertEquals(manufacturerService.findAllManufacturer().size(), MANUFACTURER_SIZE + 1);
    }

    @Test
    @Order(4)
    public void createSouvenirTest() {
        Souvenir souvenir = new Souvenir("Comix book", LocalDate.parse("1985-08-11"), BigDecimal.valueOf(11700.00), 6L);
        souvenirService.createSouvenir(souvenir);
        Assertions.assertEquals(souvenirService.findAllSouvenir().size(), SOUVENIRS_SIZE + 1);
    }

    @Test
    @Order(5)
    public void findManufacturerByIdTest() {
        Manufacturer manufacturer = manufacturerService.findAllManufacturer().get(0);
        Assertions.assertEquals(manufacturerService.findManufacturerById(manufacturer.getId()).get(), manufacturer);
    }

    @Test
    @Order(6)
    public void findSouvenirByIdTest() {
        Souvenir souvenir = souvenirService.findAllSouvenir().get(0);
        Assertions.assertEquals(souvenirService.findSouvenirById(souvenir.getId()).get(), souvenir);
    }

    @Test
    @Order(7)
    public void updateManufacturerTest() {
        Manufacturer newManufacturer = manufacturerService.findManufacturerById(MANUFACTURER_SIZE + 1).get();
        newManufacturer.setName("DC Universe");
        newManufacturer.setCountry("Canada");
        manufacturerService.updateManufacturer(newManufacturer);
        Assertions.assertNotEquals(manufacturerService.findManufacturerById(MANUFACTURER_SIZE + 1).get().getName(), "DC");
        Assertions.assertNotEquals(manufacturerService.findManufacturerById(MANUFACTURER_SIZE + 1).get().getCountry(), "USA");
    }

    @Test
    @Order(8)
    public void updateSouvenirTest() {
        Souvenir newSouvenir = souvenirService.findSouvenirById(SOUVENIRS_SIZE + 1).get();
        newSouvenir.setName("Visual Novel");
        newSouvenir.setDate(LocalDate.parse("2012-12-07"));
        newSouvenir.setPrice(BigDecimal.valueOf(12000L));
        souvenirService.updateSouvenir(newSouvenir);
        Assertions.assertNotEquals(souvenirService.findSouvenirById(SOUVENIRS_SIZE + 1).get().getName(), "Comix book");
        Assertions.assertNotEquals(souvenirService.findSouvenirById(SOUVENIRS_SIZE + 1).get().getDate(), LocalDate.parse("1985-08-11"));
        Assertions.assertNotEquals(souvenirService.findSouvenirById(SOUVENIRS_SIZE + 1).get().getPrice(), BigDecimal.valueOf(11700.00));
    }

    @Test
    @Order(9)
    public void deleteSouvenirTest() {
        souvenirService.deleteSouvenir(SOUVENIRS_SIZE + 1);
        Assertions.assertEquals(souvenirService.findAllSouvenir().size(), SOUVENIRS_SIZE);
    }

    @Test
    @Order(10)
    public void deleteManufacturerTest() {
        manufacturerService.deleteManufacturer(MANUFACTURER_SIZE + 1);
        Assertions.assertEquals(manufacturerService.findAllManufacturer().size(), MANUFACTURER_SIZE);
    }

    @Test
    @Order(11)
    public void findByManufacturerTest() {
        Manufacturer manufacturer = manufacturerService.findAllManufacturer().get(0);
        Souvenir souvenir = souvenirService.findByManufacturer(manufacturer.getId()).get(0);
        Assertions.assertEquals(manufacturer.getSouvenirs().get(0), souvenir);
    }

    @Test
    @Order(12)
    public void findByCountryTest() {
        Manufacturer manufacturer = manufacturerService.findAllManufacturer().get(0);
        Souvenir souvenir = souvenirService.findByCountry(manufacturer.getCountry()).get(0);
        Assertions.assertEquals(manufacturer.getSouvenirs().get(0), souvenir);
    }

    @Test
    @Order(13)
    public void findByYearTest() {
        int year = souvenirService.findAllSouvenir().get(0).getDate().getYear();
        Souvenir souvenir = souvenirService.findByYear(year).get(0);
        Assertions.assertEquals(year, souvenir.getDate().getYear());
    }

    @Test
    @Order(14)
    public void findByLessPriceTest() {
        BigDecimal testPrice = new BigDecimal(300);
        Manufacturer manufacturer = manufacturerService.findByLessPrice(testPrice).get(0);
        Assertions.assertEquals(manufacturer, manufacturerService.findManufacturerById(5L).get());
    }

    @Test
    @Order(15)
    public void findAllManufacturerAndSouvenirTest() {
        Map<Manufacturer, List<Souvenir>> map = manufacturerService.findAllManufacturerAndSouvenir();
        int numberOfManufacturers = map.size();
        int totalNumberOfSouvenirs = map.values().stream()
                .map(List::size)
                .mapToInt(Integer::intValue)
                .sum();
        Assertions.assertEquals(numberOfManufacturers, MANUFACTURER_SIZE);
        Assertions.assertEquals(totalNumberOfSouvenirs, SOUVENIRS_SIZE);
    }

    @Test
    @Order(16)
    public void findBySouvenirAndYearTest() {
        Manufacturer manufacturer = manufacturerService.findAllManufacturer().get(0);
        String name = manufacturer.getSouvenirs().get(0).getName();
        int year = manufacturer.getSouvenirs().get(0).getDate().getYear();
        Manufacturer otherManufacturer = manufacturerService.findBySouvenirAndYear(name, year).get(0);
        Assertions.assertEquals(otherManufacturer, manufacturer);
    }

    @AfterAll
    public static void deleteManufacturerFile() {
        File file = new File("manufacturers.json");
        if (file.exists()) {
            file.delete();
        }
    }

    @AfterAll
    public static void deleteSouvenirFile() {
        File file = new File("souvenirs.json");
        if (file.exists()) {
            file.delete();
        }
    }
}
