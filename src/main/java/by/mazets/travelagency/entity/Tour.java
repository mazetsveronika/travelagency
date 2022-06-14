package by.mazets.travelagency.entity;

import by.mazets.travelagency.exception.TravelAgencyException;

import java.math.BigDecimal;

public class Tour extends AbstractEntity{

    private String tourType;
    private BigDecimal tourPrice;
    private boolean hot;
    //todo



    public Tour(int id, String tourType, BigDecimal tourPrice, boolean hot) {
        super(id);
        this.tourType = tourType;
        this.tourPrice = tourPrice;
        this.hot = hot;
    }

    public Tour() {
        super();
    }

    public String getTourType() {
        return tourType;
    }

    public void setTourType(String tourType)throws TravelAgencyException {
        if (tourType != null) {
            this.tourType = tourType;
        } else {
            throw new TravelAgencyException("Incorrect Tour Type value.");
        }
    }

    public BigDecimal getTourPrice() {
        return tourPrice;
    }

    public void setPrice(BigDecimal tourPrice){
        this.tourPrice = tourPrice;
        }


    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Tour tour = (Tour) o;

        if (hot != tour.hot) return false;
        if (tourType != null ? !tourType.equals(tour.tourType) : tour.tourType != null) return false;
        return tourPrice != null ? tourPrice.equals(tour.tourPrice) : tour.tourPrice == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (tourType != null ? tourType.hashCode() : 0);
        result = 31 * result + (tourPrice != null ? tourPrice.hashCode() : 0);
        result = 31 * result + (hot ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tour{");
        sb.append("tourType='").append(tourType).append('\'');
        sb.append("tourPrice='").append(tourPrice).append('\'');
        sb.append("hot='").append(hot).append('\'');
        return sb.toString();
    }
}
