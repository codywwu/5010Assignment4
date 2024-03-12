package models;

public class Company {
    String date;
    String high;
    String low;
    Boolean hasValidDate;

    public Company(String date, String high, String low, Boolean hasValidDate) {
        this.date = date;
        this.high = high;
        this.low = low;
        this.hasValidDate = hasValidDate;
    }

    public Boolean getHasValidDate() {
        return hasValidDate;
    }

    public void setHasValidDate(Boolean hasValidDate) {
        this.hasValidDate = hasValidDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }
}
