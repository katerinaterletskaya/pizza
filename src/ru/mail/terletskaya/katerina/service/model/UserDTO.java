package ru.mail.terletskaya.katerina.service.model;

public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private String role;

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    private UserDTO(Builder builder) {
        id = builder.id;
        username = builder.username;
        password = builder.password;
        role = builder.role;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer id;
        private String username;
        private String password;
        private String  role;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder username(String val) {
            username = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public Builder role(String val) {
            role = val;
            return this;
        }

        public UserDTO build() {
            return new UserDTO(this);
        }
    }
}
