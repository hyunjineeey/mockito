package edu.umn.cs.csci3081w.lab11;

public class ProsciuttoDecorator extends ToppingDecorator {

  public ProsciuttoDecorator(Pizza pizza){
    super(pizza);
    cost = 3;
  }

  public int getCost(){
    return cost+pizza.getCost();
  }

  public String getDescription(){
    return "prosciutto "+pizza.getDescription();
  }

}
