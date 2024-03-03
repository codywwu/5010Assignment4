public class StockController {
  private Stock model;
  private Stock view;

  public StockController(Stock model, Stock view) {
    this.model = model;
    this.view = view;
  }

  public void setStockCompanyName(String name ){
    model.setCompanyName(name);
  }

  public void setStockVolum(long volume){
    model.setVolume(volume);
  }

  public void setStockLocalHigh(long localHigh) {
    model.setLocalHigh(localHigh);
  }

  public void setStockLocalLow(long localLow) {
    model.setLocalLow(localLow);
  }

  public void setStockTimeStamp(String timeStamp){
    model.setTimeStamp(timeStamp);
  }
}
