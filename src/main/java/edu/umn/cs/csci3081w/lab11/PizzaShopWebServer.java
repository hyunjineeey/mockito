package edu.umn.cs.csci3081w.lab11;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(
    value = "/lab11mockito",
    subprotocols = {"web_server"}
)
public class PizzaShopWebServer {

  private Session session;

  @OnOpen
  public void onOpen(Session session) {
    this.session = session;
  }

  @OnMessage
  public void onMessage(String message) {
    JsonObject orderJson = JsonParser.parseString(message).getAsJsonObject();
    String order = orderJson.get("text").getAsString();
    String[] orderArray = order.split(" ");
    String type = "";
    String toppings = "";
    if(orderArray.length==1){
      type = orderArray[0];
    }
    else if(orderArray.length==2){
      type = orderArray[0];
      toppings = orderArray[1];
    }
    else{
      JsonObject confirmation = new JsonObject();
      confirmation.addProperty("text", "The order is not right.");
      sendJson(confirmation);
      return;
    }
    PizzaFactory pizzaFactory = new OrderBasedPizzaFactory();
    Pizza pizza = pizzaFactory.makePizza(type,toppings);
    if(pizza==null){
      JsonObject confirmation = new JsonObject();
      confirmation.addProperty("text", "We do not have this type of pizza.");
      sendJson(confirmation);
      return;
    }
    else {
      JsonObject confirmation = new JsonObject();
      confirmation.addProperty("text", "Cost:"+pizza.getCost()+" Order:"+pizza.getDescription());
      sendJson(confirmation);
      return;
    }
  }

  public void sendJson(JsonObject message) {
    try {
      session.getBasicRemote().sendText(message.toString());
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  @OnError
  public void onError(Throwable e) {
    e.printStackTrace();
  }

  @OnClose
  public void onClose(Session session) {
    //make session null as the session is closed
    this.session = null;
  }
}
