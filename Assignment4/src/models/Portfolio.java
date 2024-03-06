package models;

import java.util.HashSet;
import models.Stock;

/**
 * Using Hashset as the data structure of models.profolio to store stocks.
 */
public class Profolio {
  public HashSet<Stock> stockSet;
  int quantity;

  String name;
  /**
   * Quantity of the whole stock.
   * @param quantity
   */
  public Profolio(String name,int quantity){
    this.name =name;this.quantity = quantity;
  }

  /**
   * Add new models.Stock by quantity.
   * @param newStock the new stock that will be added into the profoilo.
   * @param quantity integer number.
   */
  public void addStock(Stock newStock, int quantity){
    // if stock name has already been added.
    // Then add the stock into the original pile.
    //TODO

    //Add the stock by quantity
    for(int i=0; i<=quantity ;i++) {
      stockSet.add(newStock);
      // Also update the constructor.
      this.quantity++;
    }
  }

}
