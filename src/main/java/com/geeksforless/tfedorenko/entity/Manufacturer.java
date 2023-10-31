package com.geeksforless.tfedorenko.entity;

import com.geeksforless.tfedorenko.utils.colorHelper.ColorsMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Manufacturer extends BaseEntity {

    private String name;

    private String country;


    private List<Souvenir> souvenirs = new ArrayList<>();

    public Manufacturer() {
    }

    public Manufacturer(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Souvenir> getSouvenirs() {
        return souvenirs;
    }

    public void setSouvenirs(List<Souvenir> souvenirs) {
        this.souvenirs = souvenirs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manufacturer that = (Manufacturer) o;
        return Objects.equals(name, that.name) && Objects.equals(country, that.country) && Objects.equals(souvenirs, that.souvenirs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, souvenirs);
    }

    @Override
    public String toString() {
        return ColorsMessage.PURPLE + "Manufacturer{" +
                "name ='" + name + '\'' +
                ", country ='" + country + '\'' +
                '}';
    }
}
