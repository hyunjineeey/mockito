package edu.umn.cs.csci3081w.lab11;

public class OrderBasedPizzaFactory extends PizzaFactory {

  public Pizza makePizza(String type, String toppings){
    Pizza pizza = null;
    if(type.equals("margherita")){
      pizza = new Margherita();
    }
    else if(type.equals("napoli")){
      pizza = new Napoli();
    }
    else{
      return null;
    }
    String toppingsArray[] = toppings.split(",");
    for(String topping:toppingsArray){
      if(topping.equals("olives")){
        pizza = new OlivesDecorator(pizza);
      }
      else if(topping.equals("mushrooms")) {
        pizza = new MushroomsDecorator(pizza);
      }
      else if(topping.equals("prosciutto")) {
        pizza = new ProsciuttoDecorator(pizza);
      }
    }
    return pizza;
  }

}
