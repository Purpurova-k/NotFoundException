package ru.netology.domain;

import lombok.Data;

import java.util.Objects;

@Data
public class TShirt extends Product {
    private String producer;

    public TShirt() {
    }

    public TShirt(int id, String name, int price, String producer) {
        super(id, name, price);
        this.producer = producer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TShirt tShirt = (TShirt) o;
        return Objects.equals(producer, tShirt.producer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), producer);
    }

    @Override
    public String toString() {
        return "TShirt{" +
                "producer='" + producer + '\'' +
                '}';
    }
}
