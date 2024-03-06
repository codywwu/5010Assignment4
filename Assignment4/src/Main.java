import controller.Controller;
import models.Model;
import views.View;

public class Main {
  public static void main(String[] args) {
    // Create instances of the MVC components
    Model model = new Model();
    View view = new View();
    Controller controller = new Controller(model, view);
    // Start the application by invoking the controller to handle user input
    controller.intro();
  }

}
