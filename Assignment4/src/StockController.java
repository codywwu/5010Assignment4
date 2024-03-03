public class Controller {
  private Stock model;
  private Stock view;

  public Controller(Stock model, Stock view) {
    this.model = model;
    this.view = view;
  }

  public void setStockCompanyName(String name ){
    model.setCompanyName(name);
  }
}
