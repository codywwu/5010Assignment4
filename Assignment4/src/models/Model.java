package models;

import java.util.ArrayList;
import java.util.List;

public class Model {
  private List<String> data; // Example: Storing user input data
  XMLDatabase xmlDatabase = new XMLDatabase();

  // Constructor
  public Model() {
    this.data = new ArrayList<>();
  }

  public Boolean checkInputName(String name){
    return xmlDatabase.checkName(name);
  }
}
