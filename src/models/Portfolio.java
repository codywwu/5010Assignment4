package models;

import java.util.ArrayList;

/**
 * Using Hashset as the data structure of models.Portfolio to store stocks.
 */
public class Portfolio {
  public ArrayList<Stock> stockArrayList = new ArrayList<>();


  public String name;
  /**
   * Quantity of the whole stock.
   * @param name The name of the portfolio.
   */
  public Portfolio(String name){
    this.name =name;
  }

  /**
   * Add new models.Stock by quantity.
   * @param newStock the new stock that will be added into the portfolio.
   */
  public void addStock(Stock newStock){
    // if stock name has already been added.
    // Then add the stock into the original pile.
    //TODO
    //Add the stock by quantity

      stockArrayList.add(newStock);
      // Also update the constructor.
  }

  /**
   * Get the quantity of the portfolios.
   * @return number of the portfolios.
   */
  public int getQuantity() {
    return stockArrayList.size();
  }

  /**
   * stocks inside the portofolios.
   * @return stocklist.
   */
  public ArrayList<Stock> getStocks(){
    return stockArrayList;
  }

  /**
   * get the total shares from a portofolio list.
   * @return number of all shares.
   */
  public int getTotalShares(){
    int result = 0;
    for (Stock stock : stockArrayList) {
      result += (int) stock.getUserShared();
    }
    return result;
  }
}
