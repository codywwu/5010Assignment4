import controller.Controller;
import java.io.IOException;
import java.io.InputStreamReader;
import models.Model;
import views.View;

/**
 * main method to run the program.
 */
public class Main {
  public static void main(String[] args) throws IOException {
    // Create instances of the MVC components
    Model model = new Model();
    View view =new View(System.out);
    Controller controller = new Controller(model, view,new InputStreamReader(System.in), System.out);
    controller.intro();

    // Start the application by invoking the controller to handle user input

  }

}
