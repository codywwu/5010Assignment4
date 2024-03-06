package models;

import java.util.ArrayList;

public interface UserInterface {
  public String getUserName();
  public String getPassWord();
  public double getBuyingPower();

  /**
   * add a profolio into the user's profolio list.
   * @return the added this profolio list into user's current list.
   */
  public ArrayList<Profolio> addProfolio(Profolio newProfolio);

  /**
   * get the profolio by idex.
   * @return return the profolio by requested index.
   */
  public Profolio getProfolio(int i);
}
