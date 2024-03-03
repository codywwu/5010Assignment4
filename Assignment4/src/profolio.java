import java.util.HashSet;
import java.util.Set;

public class profolio {
  public HashSet<Stock> stockSet;
  int quantity;
  public profolio(){
  }

  /**
   * Add by quantity.
   * @param newStock the new stock that will be added into the profoilo.
   * @param quantity integer number.
   */
  public void add(Stock newStock, int quantity){
    for(int i=0; i<=quantity ;i++) {
      stockSet.add(newStock);
    }
  }


}
