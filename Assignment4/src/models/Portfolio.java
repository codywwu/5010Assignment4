package models;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Using Hashset as the data structure of models.Portfolio to store stocks.
 */
public class Portfolio {
  public ArrayList<Stock> stockArrayList = new ArrayList<>();
  int quantity;

  String name;
  /**
   * Quantity of the whole stock.
   * @param quantity
   */
  public Portfolio(String name,int quantity){
    this.name =name;
    this.quantity = quantity;
  }

  /**
   * Add new models.Stock by quantity.
   * @param newStock the new stock that will be added into the portfolio.
   * @param quantity integer number.
   */
  public void addStock(Stock newStock, int quantity){
    // if stock name has already been added.
    // Then add the stock into the original pile.
    //TODO

    //Add the stock by quantity
    for(int i=0; i<=quantity ;i++) {
      stockArrayList.add(newStock);
      // Also update the constructor.
      this.quantity++;
    }
  }

  public Stock getStock(int i){
    return stockArrayList.get(i);
  }

}
