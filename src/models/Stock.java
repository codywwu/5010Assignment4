package models;

public class Stock implements StockInterface {
  private String companyName;

  private long volume;
  private long localHigh;
  private long localLow;
  private final long userShared;
  public Stock(String companyName,long userShared){
    this.companyName= companyName;
    this.userShared=userShared;
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

}
