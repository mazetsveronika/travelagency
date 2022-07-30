package by.mazets.travelagency.entity;

import by.mazets.travelagency.entity.type.RoleType;
import by.mazets.travelagency.exception.TravelAgencyDataWrongException;


import java.math.BigDecimal;

/**
 * Class {@code User}
 *
 * @author Veronika Mazets
 * @version 1.0 28/07/2022
 */

public class User extends AbstractEntity {

    private String name;
    private String surname;
    private double discount;
    private BigDecimal money;
    private String email;
    private String login;
    private String password;
    private RoleType role;

    public User() {
    }

    public User(int id, String name, String surname, double discount, BigDecimal money, String email, String login, String password, RoleType role) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.discount = discount;
        this.money = money;
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws TravelAgencyDataWrongException {
        if (name != null && name != "") {
            this.name = name;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect name value.");
        }
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) throws TravelAgencyDataWrongException {
        if (surname != null && surname != "") {
            this.surname = surname;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect surname value.");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws TravelAgencyDataWrongException {
        if (email != null && email != "") {
            this.email = email;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect email value.");
        }
    }

    public double getDiscount() {
        return discount;
    }

    /**
     * method sets the value of a personal discount from 0 to 100 percent
     *
     * @param discount
     * @throws TravelAgencyDataWrongException
     */
    public void setDiscount(double discount) throws TravelAgencyDataWrongException {
        if (discount >= 0 && discount <= 100) {
            this.discount = discount;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect discount value, it will be from 0 to 100%.");
        }
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) throws TravelAgencyDataWrongException {
        if (login != null && login != "") {
            this.login = login;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect login value.");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws TravelAgencyDataWrongException {
        if (password != null && password != "") {
            this.password = password;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect password value.");
        }
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) throws TravelAgencyDataWrongException {
        if (role != null) {
            this.role = role;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect role value.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (Double.compare(user.discount, discount) != 0) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;
        if (money != null ? !money.equals(user.money) : user.money != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        return role == user.role;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        temp = Double.doubleToLongBits(discount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (money != null ? money.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("name='").append(name).append('\'');
        sb.append("surname='").append(surname).append('\'');
        sb.append("discount='").append(discount).append('\'');
        sb.append("money='").append(discount).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", role=").append(role);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }

    public static class UserBuilder {
        private final User user;


        public UserBuilder() {
            user = new User();
        }


        public UserBuilder setLogin(String login) {
            user.login = login;
            return this;
        }

        public UserBuilder setSurname(String surname) {
            user.surname = surname;
            return this;
        }


        public UserBuilder setName(String name) {
            user.name = name;
            return this;
        }


        public UserBuilder setEmail(String email) {
            user.email = email;
            return this;
        }


        public UserBuilder setUserRole(RoleType role) {
            user.role = role;
            return this;
        }

        public User getUser() {
            return user;
        }

        public User build() {
            return user;
        }


        }
    }
