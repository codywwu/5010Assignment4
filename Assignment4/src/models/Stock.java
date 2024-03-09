package models;

public class Stock implements StockInterface {
  private String companyName;

  private long volume;
  private long localHigh;
  private long localLow;
  private String timeStamp;
  private long userShared;
  public Stock(String companyName,long userShared,String timeStamp){
    this.companyName= companyName;
    this.userShared=userShared;
    this.timeStamp = timeStamp;
  }
  /**
   * Get companies name.
   *
   * @return String of the companies name.
   */
  @Override
  public String getCompanyName() {
    return companyName;
  }


  public long getUserShared(){return userShared;}

  /**
   * The total share of the company.
   *
   * @return long value of the total share.
   */
  @Override
  public long getVolume() {
    return volume;
  }

  /**
   * get the local high price of the company per share.
   *
   * @return
   */
  @Override
  public double getLocalHigh() {
    return localHigh;
  }

  /**
   * get the local low price of the company per share.
   *
   * @return
   */
  @Override
  public double getLocalLow() {
    return localLow;
  }

  /**
   * Get the time of the current stage.
   *
   * @return
   */
  @Override
  public String getTimeStamp() {
    return timeStamp;
  }

  /**
   * Get the open value of given date.
   *
   * @param date
   * @return double value when open market by date
   */
  @Override
  public double getOpenByDate(String date) {
    return 0;
  }

  /**
   * Get close value of given date.
   *
   * @param date
   * @return double value when close market by date
   */
  @Override
  public double getCloseByDate(String date) {
    return 0;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public void setVolume(long volume) {
    this.volume = volume;
  }

  public void setLocalHigh(long localHigh) {
    this.localHigh = localHigh;
  }

  public void setLocalLow(long localLow) {
    this.localLow = localLow;
  }

  public void setTimeStamp(String timeStamp) {
    this.timeStamp = timeStamp;
  }
}
