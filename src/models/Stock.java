package models;

/**
 * Represents a stock with details about a company's shares,
 * including the company name, volume, local high and low prices,
 * and user-shared information.
 */
public class Stock implements StockInterface {
  /**
   * The name of the company.
   */
  private String companyName;

  /**
   * The total volume of shares.
   */
  private long volume;

  /**
   * The highest price of the company's share locally.
   */
  private long localHigh;

  /**
   * The lowest price of the company's share locally.
   */
  private long localLow;

  /**
   * The number of shares shared by the user.
   */
  private final long userShared;

  /**
   * Constructs a new Stock with the specified company name and the number of shares  by the user.
   *
   * @param companyName The name of the company.
   * @param userShared  The number of shares  by the user.
   */
  public Stock(String companyName, long userShared) {
    this.companyName = companyName;
    this.userShared = userShared;
  }

  /**
   * Returns the company name.
   *
   * @return A string representing the company's name.
   */
  @Override
  public String getCompanyName() {
    return companyName;
  }

  /**
   * Returns the number of shares  by the user.
   *
   * @return The total number of user-shared shares.
   */
  public long getUserShared() {
    return userShared;
  }

  /**
   * Returns the total volume of shares.
   *
   * @return The total volume of shares.
   */
  @Override
  public long getVolume() {
    return volume;
  }

  /**
   * Returns the local high price of the company's share.
   *
   * @return The local high price per share.
   */
  @Override
  public double getLocalHigh() {
    return localHigh;
  }

  /**
   * Returns the local low price of the company's share.
   *
   * @return The local low price per share.
   */
  @Override
  public double getLocalLow() {
    return localLow;
  }

  /**
   * Sets the company name.
   *
   * @param companyName The new company name.
   */
  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  /**
   * Sets the total volume of shares.
   *
   * @param volume The new total volume of shares.
   */
  public void setVolume(long volume) {
    this.volume = volume;
  }

  /**
   * Sets the local high price of the company's share.
   *
   * @param localHigh The new local high price per share.
   */
  public void setLocalHigh(long localHigh) {
    this.localHigh = localHigh;
  }

  /**
   * Sets the local low price of the company's share.
   *
   * @param localLow The new local low price per share.
   */
  public void setLocalLow(long localLow) {
    this.localLow = localLow;
  }
}
