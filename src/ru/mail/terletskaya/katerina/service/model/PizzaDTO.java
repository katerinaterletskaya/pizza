package ru.mail.terletskaya.katerina.service.model;

import java.math.BigDecimal;

public class PizzaDTO {
    private Integer idPizza;
    private String namePizza;
    private Integer size;
    private Integer weight;
    private BigDecimal price;
    private String imageName;

    public Integer getIdPizza() {
        return idPizza;
    }

    public String getNamePizza() {
        return namePizza;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getWeight() {
        return weight;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getImageName() {
        return imageName;
    }

    private PizzaDTO(Builder builder) {
        idPizza = builder.idPizza;
        namePizza = builder.namePizza;
        size = builder.size;
        weight = builder.weight;
        price = builder.price;
        imageName = builder.imageName;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer idPizza;
        private String namePizza;
        private Integer size;
        private Integer weight;
        private BigDecimal price;
        private String imageName;

        private Builder() {
        }

        public Builder idPizza(Integer val) {
            idPizza = val;
            return this;
        }

        public Builder namePizza(String val) {
            namePizza = val;
            return this;
        }

        public Builder size(Integer val) {
            size = val;
            return this;
        }

        public Builder weight(Integer val) {
            weight = val;
            return this;
        }

        public Builder price(BigDecimal val) {
            price = val;
            return this;
        }

        public Builder imageName(String val) {
            imageName = val;
            return this;
        }

        public PizzaDTO build() {
            return new PizzaDTO(this);
        }
    }

    @Override
    public String toString() {
        return "PizzaDTO{" +
                "idPizza=" + idPizza +
                ", namePizza='" + namePizza + '\'' +
                ", size=" + size +
                ", weight=" + weight +
                ", price=" + price +
                ", imageName='" + imageName + '\'' +
                '}';
    }
}
