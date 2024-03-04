public class view {
  public static void main(String[] args) {

    System.out.println("Welcome To Our App");
    System.out.println("Please enter a user name");

    Stock stock = new Stock("TESLA",300,40,20,"2020");
    StockController stockController = new StockController();

    stockController.setStockLocalHigh(50);

    System.out.println(stockController.getStockLocalHigh());


  }
}
