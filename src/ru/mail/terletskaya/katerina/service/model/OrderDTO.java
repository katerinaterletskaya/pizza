package ru.mail.terletskaya.katerina.service.model;

import java.math.BigDecimal;
import java.util.List;

public class OrderDTO {
    private Integer id;
    private String user;
    private BigDecimal price;
    private String status;
    private List<PizzaDTO> pizzas;

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

    public List<PizzaDTO> getPizzas() {
        return pizzas;
    }

    private OrderDTO(Builder builder) {
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
        private List<PizzaDTO> pizzas;

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

        public Builder pizzas(List<PizzaDTO> val) {
            pizzas = val;
            return this;
        }

        public OrderDTO build() {
            return new OrderDTO(this);
        }
    }
}
