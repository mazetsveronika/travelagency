package by.mazets.travelagency.entity;

import by.mazets.travelagency.entity.type.RoleType;
import by.mazets.travelagency.exception.TravelAgencyException;

import java.math.BigDecimal;

public class User extends AbstractEntity {

    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
    private BigDecimal money;
    private Integer phoneNumber;
    private Integer discount;//todo
    private RoleType role;
    //todo


    public User(int id, String name, String surname, String login, String password, String email, BigDecimal money, Integer phoneNumber, Integer discount, RoleType role) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.email = email;
        this.money = money;
        this.phoneNumber = phoneNumber;
        this.discount = discount;
        this.role = role;
    }

    public User() {
        super();
    }

    public String getName() {
        return name;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public void setName(String name) throws TravelAgencyException {
        if (name != null && name != "") {
            this.name = name;
        } else {
            throw new TravelAgencyException("Incorrect name value.");
        }
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) throws TravelAgencyException {
        if (surname != null && surname != "") {
            this.surname = surname;
        } else {
            throw new TravelAgencyException("Incorrect surname value.");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws TravelAgencyException {
        if (email != null && email != "") {
            this.email = email;
        } else {
            throw new TravelAgencyException("Incorrect email value.");
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

    public void setLogin(String login) throws TravelAgencyException {
        if (login != null && login != "") {
            this.login = login;
        } else {
            throw new TravelAgencyException("Incorrect login value.");
        }
    }

    public Integer getDiscount() {
        return discount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws TravelAgencyException {
        if (password != null && password != "") {
            this.password = password;
        } else {
            throw new TravelAgencyException("Incorrect password value.");
        }
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) throws TravelAgencyException {
        if (role != null) {
            this.role = role;
        } else {
            throw new TravelAgencyException("Incorrect role value.");
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (money != null ? !money.equals(user.money) : user.money != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(user.phoneNumber) : user.phoneNumber != null) return false;
        if (discount != null ? !discount.equals(user.discount) : user.discount != null) return false;
        return role == user.role;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (money != null ? money.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("name='").append(name).append('\'');
        sb.append("surname='").append(surname).append('\'');
        sb.append("discount='").append(discount).append('\'');
        sb.append(", money='").append(money).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
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

        public UserBuilder setPhoneNumber(Integer phoneNumber) {
            user.phoneNumber = phoneNumber;
            return this;
        }


        public UserBuilder setUserRole(RoleType role) {
            user.role = role;
            return this;
        }


        public User build() {
            return user;
        }

        public UserBuilder setId(int id) throws TravelAgencyException {
            user.setId(id);
            return this;
        }
        }
    }
