package edu.umn.cs.csci3081w.lab11;

public class OlivesDecorator extends ToppingDecorator {

  public OlivesDecorator(Pizza pizza){
    super(pizza);
    cost = 1;
  }

  public int getCost(){
    return cost+pizza.getCost();
  }

  public String getDescription(){
    return "olives "+pizza.getDescription();
  }

}
