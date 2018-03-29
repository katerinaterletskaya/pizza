package ru.mail.terletskaya.katerina.repository.model;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    private Integer id;
    private String user;
    private BigDecimal price;
    private String status;
    private List<Pizza> pizzas;

    public Integer getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    private Order(Builder builder) {
        id = builder.id;
        user = builder.user;
        price = builder.price;
        status = builder.status;
        pizzas = builder.pizzas;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer id;
        private String user;
        private BigDecimal price;
        private String status;
        private List<Pizza> pizzas;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder user(String val) {
            user = val;
            return this;
        }

        public Builder price(BigDecimal val) {
            price = val;
            return this;
        }

        public Builder status(String val) {
            status = val;
            return this;
        }

        public Builder pizzas(List<Pizza> val) {
            pizzas = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
