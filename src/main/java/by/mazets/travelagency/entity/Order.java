package by.mazets.travelagency.entity;


import by.mazets.travelagency.exception.TravelAgencyException;

import java.math.BigDecimal;

public class Order extends AbstractEntity {

    private Voucher voucher;
    private User user;
    private BigDecimal totalPrice;

    public Order() {
    }

    public Order(Voucher voucher, User user) {
        this.voucher = voucher;
        this.user = user;
    }

    public Order(int id, Voucher voucher, User user, BigDecimal totalPrice) {
        super(id);
        this.voucher = voucher;
        this.user = user;
        this.totalPrice = totalPrice;
    }


    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) throws TravelAgencyException {
        if (voucher != null) {
            this.voucher = voucher;
        } else {
            throw new TravelAgencyException("Incorrect voucher value.");
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) throws TravelAgencyException {
        if (user != null) {
            this.user = user;
        } else {
            throw new TravelAgencyException("Incorrect user value.");
        }
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
/*  public void setTotalPrice(BigDecimal totalPrice) throws TravelAgencyException {
        if (totalPrice >= 0) {//?
            this.totalPrice = totalPrice;
        } else {
            throw new TravelAgencyException("Incorrect totalPrice value.");
        }
    }

   */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Order order = (Order) o;

        if (voucher != null ? !voucher.equals(order.voucher) : order.voucher != null) return false;
        if (user != null ? !user.equals(order.user) : order.user != null) return false;
        return totalPrice != null ? totalPrice.equals(order.totalPrice) : order.totalPrice == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (voucher != null ? voucher.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (totalPrice != null ? totalPrice.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("voucher='").append(voucher).append('\'');
        sb.append("user='").append(user).append('\'');
        sb.append("totalPrice='").append(totalPrice).append('\'');
        return sb.toString();
    }

}

