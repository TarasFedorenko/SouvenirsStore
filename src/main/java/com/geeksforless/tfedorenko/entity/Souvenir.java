package com.geeksforless.tfedorenko.entity;

import com.geeksforless.tfedorenko.utils.colorHelper.ColorsMessage;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Souvenir extends BaseEntity {
    private String name;

    private LocalDate date;

    private BigDecimal price;

    private Long manufacturer_id;

    public Souvenir() {
    }

    public Souvenir(String name, LocalDate date, BigDecimal price, Long manufacturer_id) {
        this.name = name;
        this.date = date;
        this.price = price;
        this.manufacturer_id = manufacturer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getManufacturerId() {
        return manufacturer_id;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturer_id = manufacturerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Souvenir souvenir = (Souvenir) o;
        return Objects.equals(name, souvenir.name) && Objects.equals(date, souvenir.date) && Objects.equals(price, souvenir.price) && Objects.equals(manufacturer_id, souvenir.manufacturer_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, price, manufacturer_id);
    }

    @Override
    public String toString() {
        return ColorsMessage.BLUE + "Souvenir{" +
                "name='" + name + '\'' +
                ", date = '" + date + '\'' +
                ", price = '" + price + '\'' +
                ", manufacturer_id= '" + manufacturer_id + '\'' +
                '}';
    }

}
