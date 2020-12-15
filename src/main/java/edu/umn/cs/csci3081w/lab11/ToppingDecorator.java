package edu.umn.cs.csci3081w.lab11;

public abstract class ToppingDecorator extends Pizza {

  protected Pizza pizza;

  public ToppingDecorator(Pizza pizza){
    this.pizza = pizza;
  }



}
