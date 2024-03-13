package models;

/**
 * Stock interface offer various of methods.
 */
public interface StockInterface {
  /**
   * Get companies name.
   * @return String of the companies name.
   */
   String getCompanyName();

  /**
   * The total share of the company.
   * @return long value of the total share.
   */
  long getVolume();

  /**
   * get the local high price of the company per share.
   * @return get the local high price.
   */
  double getLocalHigh();

  /**
   * get the local low price of the company per share.
   * @return get the local low price.
   */
  double getLocalLow();

}
