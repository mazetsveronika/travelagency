package by.mazets.travelagency.entity;

import by.mazets.travelagency.exception.TravelAgencyException;

import java.io.Serializable;


public abstract class AbstractEntity implements Serializable, Cloneable {

    private int id;

    public AbstractEntity(int id) {
        if (id > 0) {
            this.id = id;
        }
    }

    public AbstractEntity() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) throws TravelAgencyException {
        if (id > 0) {
            this.id = id;
        } else {
            throw new TravelAgencyException("Incorrect id value.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;
        AbstractEntity that = (AbstractEntity) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AbstractEntity{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}