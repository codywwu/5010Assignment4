package controller;

import models.Stock;

public class StockController {
  private Stock model;
  private Stock view;

  public StockController() {
    this.model = this.model;
    this.view = this.view;
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

  public double getStockLocalHigh(){return model.getLocalHigh();}
}
