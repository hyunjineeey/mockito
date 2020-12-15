package edu.umn.cs.csci3081w.lab11;

public class MushroomsDecorator extends ToppingDecorator {

  public MushroomsDecorator(Pizza pizza){
    super(pizza);
    cost = 1;
  }

  public int getCost(){
    return cost+pizza.getCost();
  }

  public String getDescription(){
    return "mushrooms "+pizza.getDescription();
  }

}
