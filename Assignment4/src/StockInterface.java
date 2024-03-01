public interface StockInterface {
  /**
   * Get companies name.
   * @return String of the companies name.
   */
  public String getCompanyName();

  /**
   * The total share of the company.
   * @return long value of the total share.
   */
  public long getVolume();

  /**
   * get the local high price of the company per share.
   * @return
   */
  public double getLocalHigh();

  /**
   * get the local low price of the company per share.
   * @return
   */
  public double getLocalLow();

  /**
   * Get the time of the current stage.
   * @return
   */
  public String getTimeStamp();

  /**
   * Get the open value of given date.
   * @return double value when open market by date
   */
  public double getOpenByDate(String date);

  /**
   * Get close value of given date.
   * @return double value when close market by date
   */
  public double getCloseByDate(String date);
}
