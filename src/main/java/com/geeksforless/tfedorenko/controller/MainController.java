package com.geeksforless.tfedorenko.controller;

import com.geeksforless.tfedorenko.entity.Manufacturer;
import com.geeksforless.tfedorenko.entity.Souvenir;
import com.geeksforless.tfedorenko.service.ManufacturerService;
import com.geeksforless.tfedorenko.service.SouvenirService;
import com.geeksforless.tfedorenko.service.impl.ManufacturerServiceImpl;
import com.geeksforless.tfedorenko.service.impl.SouvenirServiceImpl;
import com.geeksforless.tfedorenko.utils.colorHelper.ColorsMessage;
import com.geeksforless.tfedorenko.utils.printer.PrintHelper;
import com.geeksforless.tfedorenko.validator.ManufacturerValidator;
import com.geeksforless.tfedorenko.validator.SouvenirValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MainController {
    private final SouvenirService souvenirService = new SouvenirServiceImpl();
    private final ManufacturerService manufacturerService = new ManufacturerServiceImpl();

    public void run() {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String select;
        System.out.println(ColorsMessage.PURPLE + "***MAIN MENU***");
        showMenu();
        try {
            while ((select = bf.readLine()) != null) {
                menu(bf, select);
            }
        } catch (IOException io) {
            throw new RuntimeException(io);
        }

    }

    private void showMenu() {
        System.out.println();
        System.out.println(ColorsMessage.YELLOW + "Souvenirs menu press - 1");
        System.out.println("Manufacturer menu press - 2");
        System.out.println("Souvenirs search press - 3");
        System.out.println("Manufacturer search press - 4");
        System.out.println(ColorsMessage.PURPLE + "QUIT press - 5");
    }

    private void menu(BufferedReader bf, String select) throws IOException {
        switch (select) {
            case "1" -> souvenirsMenu(bf);
            case "2" -> manufacturerMenu(bf);
            case "3" -> souvenirsSearch(bf);
            case "4" -> manufacturerSearch(bf);
            case "5" -> exit();
            default -> System.out.println("Wrong input");
        }
    }

    private void souvenirsMenu(BufferedReader bf) throws IOException {
        while (true) {
            System.out.println();
            System.out.println(ColorsMessage.YELLOW + "To add new souvenir press - 1");
            System.out.println("To make change in exist souvenir data press - 2");
            System.out.println("To remove souvenir press - 3");
            System.out.println("To take info about exist souvenir press - 4");
            System.out.println(ColorsMessage.PURPLE + "Back to main menu - 5");

            String choice = bf.readLine();
            switch (choice) {
                case "1" -> addSouvenir(bf);
                case "2" -> changeSouvenir(bf);
                case "3" -> removeSouvenir(bf);
                case "4" -> souvenirInfo(bf);
                case "5" -> {
                    showMenu();
                    return;
                }
                default -> System.out.println(ColorsMessage.RED + "Wrong input");
            }
        }
    }

    private void addSouvenir(BufferedReader bf) throws IOException {
        System.out.println(ColorsMessage.GREEN + "You want to create profile of new souvenir");
        System.out.println("Please enter the name of the souvenir");
        String name = bf.readLine();
        if (!SouvenirValidator.validateName(name)) {
            System.out.println(ColorsMessage.RED + "Uncorrected name");
            return;
        }
        System.out.println("Please enter date of souvenir productions ");
        LocalDate date = LocalDate.parse(bf.readLine());
        if (!SouvenirValidator.validateDate(String.valueOf(date))) {
            System.out.println(ColorsMessage.RED + "Uncorrected date");
            return;
        }
        System.out.println("Please enter price of souvenir");
        BigDecimal price = new BigDecimal(bf.readLine());
        if (!SouvenirValidator.validatePrice(String.valueOf(price))) {
            System.out.println(ColorsMessage.RED + "Uncorrected price");
            return;
        }
        System.out.println("Please enter manufacturer id");
        Long manufacturerId = Long.parseLong(bf.readLine());
        if (!SouvenirValidator.validateId(manufacturerId)) {
            System.out.println(ColorsMessage.RED + "Uncorrected id");
            return;
        }
        Souvenir newSouvenir = new Souvenir();
        newSouvenir.setName(name);
        newSouvenir.setDate(date);
        newSouvenir.setPrice(price);
        newSouvenir.setManufacturerId(manufacturerId);
        souvenirService.createSouvenir(newSouvenir);
    }

    private void changeSouvenir(BufferedReader bf) throws IOException {
        System.out.println(ColorsMessage.GREEN + "You want to make change in exist profile of souvenir");
        System.out.println("Please enter id number of exist souvenir");
        Long id = Long.parseLong(bf.readLine());
        if (!SouvenirValidator.validateId(id)) {
            System.out.println(ColorsMessage.RED + "Uncorrected id");
            return;
        }
        System.out.println("Please enter the new name of the souvenir");
        String name = bf.readLine();
        if (!SouvenirValidator.validateName(name)) {
            System.out.println(ColorsMessage.RED + "Uncorrected name");
            return;
        }
        System.out.println("Please enter new date of souvenir productions ");
        LocalDate date = LocalDate.parse(bf.readLine());
        if (!SouvenirValidator.validateDate(String.valueOf(date))) {
            System.out.println(ColorsMessage.RED + "Uncorrected date");
            return;
        }
        System.out.println("Please enter new price of souvenir");
        BigDecimal price = new BigDecimal(bf.readLine());
        if (!SouvenirValidator.validatePrice(String.valueOf(price))) {
            System.out.println(ColorsMessage.RED + "Uncorrected price");
            return;
        }
        Souvenir updatedSouvenir = souvenirService.findSouvenirById(id).orElseThrow(() -> new RuntimeException("souvenir not found"));
        updatedSouvenir.setName(name);
        updatedSouvenir.setDate(date);
        updatedSouvenir.setPrice(price);
        souvenirService.updateSouvenir(updatedSouvenir);
    }

    private void removeSouvenir(BufferedReader bf) throws IOException {
        System.out.println(ColorsMessage.GREEN + "You want to delete exist profile of souvenir");
        System.out.println("Please enter id number of exist souvenir");
        Long id = Long.parseLong(bf.readLine());
        if (!SouvenirValidator.validateId(id)) {
            System.out.println(ColorsMessage.RED + "Uncorrected id");
            return;
        }
        souvenirService.deleteSouvenir(id);
    }

    private void souvenirInfo(BufferedReader bf) throws IOException {
        System.out.println(ColorsMessage.GREEN + "You want to take information about exist souvenir");
        System.out.println("Please enter id number of exist souvenir");
        Long id = Long.parseLong(bf.readLine());
        if (!SouvenirValidator.validateId(id)) {
            System.out.println(ColorsMessage.RED + "Uncorrected id");
            return;
        }
        Souvenir souvenir = souvenirService.findSouvenirById(id).orElseThrow(() -> new RuntimeException("souvenir not found"));
        System.out.println(souvenir);
    }

    private void manufacturerMenu(BufferedReader bf) throws IOException {
        while (true) {
            System.out.println();
            System.out.println(ColorsMessage.YELLOW + "To add new manufacturer press - 1");
            System.out.println("To make change in exist manufacturer data press - 2");
            System.out.println("To remove manufacturer press - 3");
            System.out.println("To take info about exist manufacturer press - 4");
            System.out.println(ColorsMessage.PURPLE + "Back to main menu - 5");

            String choice = bf.readLine();
            switch (choice) {
                case "1" -> addManufacturer(bf);
                case "2" -> changeManufacturer(bf);
                case "3" -> removeManufacturer(bf);
                case "4" -> manufacturerInfo(bf);
                case "5" -> {
                    showMenu();
                    return;
                }
                default -> System.out.println(ColorsMessage.RED + "Wrong input");
            }
        }
    }

    private void addManufacturer(BufferedReader bf) throws IOException {
        System.out.println(ColorsMessage.GREEN + "You want to create profile of new manufacturer");
        System.out.println("Please enter the name of the manufacturer");
        String name = bf.readLine();
        if (!ManufacturerValidator.validateName(name)) {
            System.out.println(ColorsMessage.RED + "Uncorrected name");
            return;
        }
        System.out.println("Please enter country of manufacturer ");
        String country = bf.readLine();
        if (!ManufacturerValidator.validateCountryName(country)) {
            System.out.println(ColorsMessage.RED + "Uncorrected country name");
            return;
        }
        Manufacturer newManufacturer = new Manufacturer();
        newManufacturer.setName(name);
        newManufacturer.setCountry(country);
        manufacturerService.createManufacturer(newManufacturer);
    }

    private void changeManufacturer(BufferedReader bf) throws IOException {
        System.out.println(ColorsMessage.GREEN + "You want to make change in exist profile of manufacturer");
        System.out.println("Please enter id number of exist manufacturer");
        Long id = Long.parseLong(bf.readLine());
        if (!ManufacturerValidator.validateId(id)) {
            System.out.println(ColorsMessage.RED + "Uncorrected id");
            return;
        }
        System.out.println("Please enter the new name of the manufacturer");
        String name = bf.readLine();
        if (!ManufacturerValidator.validateName(name)) {
            System.out.println(ColorsMessage.RED + "Uncorrected name");
            return;
        }
        System.out.println("Please enter new country of manufacturer ");
        String country = bf.readLine();
        if (!ManufacturerValidator.validateCountryName(country)) {
            System.out.println(ColorsMessage.RED + "Uncorrected country name");
            return;
        }
        Manufacturer updatedManufacturer = manufacturerService.findManufacturerById(id).orElseThrow(() -> new RuntimeException("manufacturer not found"));
        updatedManufacturer.setName(name);
        updatedManufacturer.setCountry(country);
        manufacturerService.updateManufacturer(updatedManufacturer);
    }

    private void removeManufacturer(BufferedReader bf) throws IOException {
        System.out.println(ColorsMessage.GREEN + "You want to delete exist profile of manufacturer");
        System.out.println("Please enter id number of exist manufacturer");
        Long id = Long.parseLong(bf.readLine());
        if (!ManufacturerValidator.validateId(id)) {
            System.out.println(ColorsMessage.RED + "Uncorrected id");
            return;
        }
        manufacturerService.deleteManufacturer(id);
    }

    private void manufacturerInfo(BufferedReader bf) throws IOException {
        System.out.println(ColorsMessage.GREEN + "You want to take information about exist manufacturer");
        System.out.println("Please enter id number of exist manufacturer");
        Long id = Long.parseLong(bf.readLine());
        if (!ManufacturerValidator.validateId(id)) {
            System.out.println(ColorsMessage.RED + "Uncorrected id");
            return;
        }
        Manufacturer manufacturer = manufacturerService.findManufacturerById(id).orElseThrow(() -> new RuntimeException("manufacturer not found"));
        System.out.println(manufacturer);
    }

    private void souvenirsSearch(BufferedReader bf) throws IOException {
        while (true) {
            System.out.println();
            System.out.println(ColorsMessage.YELLOW + "To find all souvenirs press - 1");
            System.out.println("To find all souvenirs by manufacturer press - 2");
            System.out.println("To find all souvenirs by country press - 3");
            System.out.println("To find all souvenirs by year press - 4");
            System.out.println(ColorsMessage.PURPLE + "Back to main menu - 5");

            String choice = bf.readLine();
            switch (choice) {
                case "1" -> findAllSouvenirs();
                case "2" -> findSouvenirsByManufacturer(bf);
                case "3" -> findSouvenirsByCountry(bf);
                case "4" -> findSouvenirsByYear(bf);
                case "5" -> {
                    showMenu();
                    return;
                }
                default -> System.out.println(ColorsMessage.RED + "Wrong input");
            }
        }
    }

    private void findAllSouvenirs() {
        System.out.println(ColorsMessage.GREEN + "You want to check list of all souvenirs");
        Collection<Souvenir> souvenirs = souvenirService.findAllSouvenir();
        PrintHelper.printEntity(souvenirs);
    }

    private void findSouvenirsByManufacturer(BufferedReader bf) throws IOException {
        System.out.println(ColorsMessage.GREEN + "You want to check list of all souvenirs by manufacturer");
        System.out.println("Please enter manufacturer id");
        Long manufacturerId = Long.parseLong(bf.readLine());
        if (!SouvenirValidator.validateId(manufacturerId)) {
            System.out.println(ColorsMessage.RED + "Uncorrected id");
            return;
        }
        Collection<Souvenir> souvenirs = souvenirService.findByManufacturer(manufacturerId);
        PrintHelper.printEntity(souvenirs);
    }

    private void findSouvenirsByCountry(BufferedReader bf) throws IOException {
        System.out.println(ColorsMessage.GREEN + "You want to check list of all souvenirs by country of manufacturer");
        System.out.println("Please enter country");
        String country = bf.readLine();
        if (!ManufacturerValidator.validateCountryName(country)) {
            System.out.println(ColorsMessage.RED + "Uncorrected country name");
            return;
        }
        Collection<Souvenir> souvenirs = souvenirService.findByCountry(country);
        PrintHelper.printEntity(souvenirs);
    }

    private void findSouvenirsByYear(BufferedReader bf) throws IOException {
        System.out.println(ColorsMessage.GREEN + "You want to check list of all souvenirs by year of productions");
        System.out.println("Please enter year when souvenir is made");
        int yearOfProduction = Integer.parseInt(bf.readLine());
        if (!SouvenirValidator.validateYear(yearOfProduction)) {
            System.out.println(ColorsMessage.RED + "Uncorrected value of year \n Must be more than 1900 and less than 2024");
            return;
        }
        Collection<Souvenir> souvenirs = souvenirService.findByYear(yearOfProduction);
        PrintHelper.printEntity(souvenirs);
    }

    private void manufacturerSearch(BufferedReader bf) throws IOException {
        while (true) {
            System.out.println();
            System.out.println(ColorsMessage.YELLOW + "To find all manufacturer press - 1");
            System.out.println("To find all manufacturer by price souvenirs less than specified press - 2");
            System.out.println("To find all manufacturer with assortment souvenirs press - 3");
            System.out.println("To find all manufacturers by the name of the souvenir " +
                    "and the year of its production press - 4");
            System.out.println(ColorsMessage.PURPLE + "Back to main menu - 5");

            String choice = bf.readLine();
            switch (choice) {
                case "1" -> findAllManufacturers();
                case "2" -> findManufacturersByLessPrice(bf);
                case "3" -> findManufacturersWithSouvenirs();
                case "4" -> findManufacturersBySouvenirsNameAndProductionYear(bf);
                case "5" -> {
                    showMenu();
                    return;
                }
                default -> System.out.println(ColorsMessage.RED + "Wrong input");
            }
        }
    }

    private void findAllManufacturers() {
        System.out.println(ColorsMessage.GREEN + "You want to check list of all manufacturers");
        Collection<Manufacturer> manufacturers = manufacturerService.findAllManufacturer();
        PrintHelper.printEntity(manufacturers);

    }

    private void findManufacturersByLessPrice(BufferedReader bf) throws IOException {
        System.out.println(ColorsMessage.GREEN + "You want to check list of all manufacturers where souvenirs price less than you want");
        System.out.println("Please enter upper border of price");
        BigDecimal price = new BigDecimal(bf.readLine());
        if (!SouvenirValidator.validatePrice(String.valueOf(price))) {
            System.out.println(ColorsMessage.RED + "Uncorrected price");
            return;
        }
        Collection<Manufacturer> manufacturers = manufacturerService.findByLessPrice(price);
        PrintHelper.printEntity(manufacturers);
    }

    private void findManufacturersWithSouvenirs() {
        System.out.println(ColorsMessage.GREEN + "You want to check list of all manufacturers with their souvenirs");
        Map<Manufacturer, List<Souvenir>> manufacturerSouvenirMap = manufacturerService.findAllManufacturerAndSouvenir();
        for (Map.Entry<Manufacturer, List<Souvenir>> entry : manufacturerSouvenirMap.entrySet()) {
            Manufacturer manufacturer = entry.getKey();
            List<Souvenir> souvenirs = entry.getValue();
            System.out.println(ColorsMessage.PURPLE + "Manufacturer: " + manufacturer.getName());
            System.out.println(ColorsMessage.BLUE + "Souvenirs:");
            for (Souvenir souvenir : souvenirs) {
                System.out.println(ColorsMessage.YELLOW + "- " + ColorsMessage.BLUE + souvenir.getName());
            }
            System.out.println();
        }
    }

    private void findManufacturersBySouvenirsNameAndProductionYear(BufferedReader bf) throws IOException {
        System.out.println(ColorsMessage.GREEN + "You want to check list of all manufacturers with specified name and year of production of souvenir");
        System.out.println("Please enter name of souvenir");
        String souvenirName = bf.readLine();
        System.out.println("Please enter year of production of  souvenir");
        int yearOfProduction = Integer.parseInt(bf.readLine());
        if (!SouvenirValidator.validateYear(yearOfProduction)) {
            System.out.println(ColorsMessage.RED + "Uncorrected value of year \n Must be more than 1900 and less than 2024");
            return;
        }
        Collection<Manufacturer> manufacturers = manufacturerService.findBySouvenirAndYear(souvenirName, yearOfProduction);
        PrintHelper.printEntity(manufacturers);
    }

    private void exit() {
        System.out.println(ColorsMessage.PURPLE + "Good bye");
        System.exit(0);
    }
}
