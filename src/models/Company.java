package models;

/**
 * The {@code Company} class represents company stock information on a specific date.
 * It holds the high and low stock values for the date, along with a flag indicating
 * whether the date is considered valid.
 */
public class Company {
    /**
     * The date of the stock information, typically in YYYY-MM-DD format.
     */
    String date;

    /**
     * The highest stock value of the company on the given date.
     */
    String high;

    /**
     * The lowest stock value of the company on the given date.
     */
    String low;

    /**
     * A flag indicating whether the provided date is valid for stock information.
     */
    Boolean hasValidDate;

    /**
     * Constructs a new {@code Company} with the specified date, high and low stock values,
     * and a flag indicating if the date is valid.
     *
     * @param date         the date of the stock information
     * @param high         the highest stock value on the given date
     * @param low          the lowest stock value on the given date
     * @param hasValidDate a flag indicating whether the date is valid
     */
    public Company(String date, String high, String low, Boolean hasValidDate) {
        this.date = date;
        this.high = high;
        this.low = low;
        this.hasValidDate = hasValidDate;
    }

    /**
     * Returns whether the date is considered valid for stock information.
     *
     * @return {@code true} if the date is valid; {@code false} otherwise.
     */
    public Boolean getHasValidDate() {
        return hasValidDate;
    }

    /**
     * Returns the date of the stock information.
     *
     * @return the date of the stock information
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of the stock information.
     *
     * @param date the new date of the stock information
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Returns the highest stock value on the given date.
     *
     * @return the highest stock value on the given date
     */
    public String getHigh() {
        return high;
    }

    /**
     * Sets the highest stock value of the company on the given date.
     *
     * @param high the new highest stock value
     */
    public void setHigh(String high) {
        this.high = high;
    }

    /**
     * Returns the lowest stock value on the given date.
     *
     * @return the lowest stock value on the given date
     */
    public String getLow() {
        return low;
    }

    /**
     * Sets the lowest stock value of the company on the given date.
     *
     * @param low the new lowest stock value
     */
    public void setLow(String low) {
        this.low = low;
    }
}
