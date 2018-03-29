package ru.mail.terletskaya.katerina.repository.model;

import java.math.BigDecimal;

public class OrderItem {
    private Integer id;
    private Integer userId;
    private String user;
    private BigDecimal price;
    private String status;

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
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

    private OrderItem(Builder builder) {
        id = builder.id;
        userId = builder.userId;
        user = builder.user;
        price = builder.price;
        status = builder.status;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer id;
        private Integer userId;
        private String user;
        private BigDecimal price;
        private String status;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder userId(Integer val) {
            userId = val;
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

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
