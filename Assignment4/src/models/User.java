package models;

import java.util.ArrayList;

import models.UserInterface;

public class User implements UserInterface {
  String userName;

  String passWord;

  double buyingPower;

  ArrayList<Profolio> profolioList = new ArrayList<>();

  public User(String userName, String passWord, double buyingPower){
    this.userName = userName;
    this.passWord = passWord;
    this.buyingPower = buyingPower;
  }
  /**
   * @return
   */
  @Override
  public String getUserName() {
    return userName;
  }

  public void setBuyingPower(double buyingPower){
    this.buyingPower = buyingPower;
  }
  public void setPassWord(String passWord){
    this.passWord = passWord;
  }

  public void setUserName(String userName){ this.userName = userName;}
  /**
   * @return
   */
  @Override
  public String getPassWord() {
    return passWord;
  }

  /**
   * @return
   */
  @Override
  public double getBuyingPower() {
    return buyingPower;
  }

  /**
   * add a profolio into the user's profolio list.
   *
   * @return the added this profolio list into user's current list.
   */
  @Override
  public ArrayList<Profolio> addProfolio(Profolio newProfolio) {
    return null;
  }

  /**
   * get the profolio by idex.
   *
   * @param i
   * @return return the profolio by requested index.
   */
  @Override
  public Profolio getProfolio(int i) {
    return null;
  }
}
