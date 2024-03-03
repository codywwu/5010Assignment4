public class Company implements CompanyInterface{
  String symbol;

  Stock stockInfo;
  public Company(String symbol, Stock stockInfo ){
    this.symbol = symbol;
    this.stockInfo = stockInfo;

  }
  /**
   * Get the company symbol.
   * eg, "GOOG" for Google.
   *
   * @return the company symbol string.
   */
  @Override
  public String getSymbol() {
    return symbol;
  }


  public Stock getStockInfo(){
    return stockInfo;
  }
}
