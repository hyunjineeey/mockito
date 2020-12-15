package edu.umn.cs.csci3081w.lab11;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;


import com.google.gson.JsonObject;
import javax.websocket.Session;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class PizzaShopWebServerTest {

  @Test
  public void testMargheritaWithOlives() {
    //create a spy we want to preserve the implementation of the methods
    PizzaShopWebServer pizzaShopWebServerSpy = spy(PizzaShopWebServer.class);
    //tell the spy object to not do anything when the method sendJson is called with a Json object parameter
    doNothing().when(pizzaShopWebServerSpy).sendJson(Mockito.isA(JsonObject.class));
    //create a dummy object for the session as we need it to open the session
    Session sessionDummy = mock(Session.class);
    //open session
    pizzaShopWebServerSpy.onOpen(sessionDummy);
    //prepare input to send to the pizza shop web server
    JsonObject order = new JsonObject();
    order.addProperty("text", "margherita olives");
    //send the input to the pizza shop
    pizzaShopWebServerSpy.onMessage(order.toString());
    //capture the json object passed to the sendJson method of the pizza shop spy
    ArgumentCaptor<JsonObject> messageCaptor = ArgumentCaptor.forClass(JsonObject.class);
    verify(pizzaShopWebServerSpy).sendJson(messageCaptor.capture());
    //check that we have the expected output
    JsonObject message = messageCaptor.getValue();
    String expected = "Cost:6 Order:olives margherita";
    assertEquals(expected, message.get("text").getAsString());
  }

  @Test
  public void testNoType() {
    //create a spy we want to preserve the implementation of the methods
    PizzaShopWebServer pizzaShopWebServerSpy = spy(PizzaShopWebServer.class);
    //tell the spy object to not do anything when the method sendJson is called with a Json object parameter
    doNothing().when(pizzaShopWebServerSpy).sendJson(Mockito.isA(JsonObject.class));
    //create a dummy object for the session as we need it to open the session
    Session sessionDummy = mock(Session.class);
    //open session
    pizzaShopWebServerSpy.onOpen(sessionDummy);
    //prepare input to send to the pizza shop web server
    JsonObject order = new JsonObject();
    order.addProperty("text", "olives");
    //send the input to the pizza shop
    pizzaShopWebServerSpy.onMessage(order.toString());
    //capture the json object passed to the sendJson method of the pizza shop spy
    ArgumentCaptor<JsonObject> messageCaptor = ArgumentCaptor.forClass(JsonObject.class);
    verify(pizzaShopWebServerSpy).sendJson(messageCaptor.capture());
    //check that we have the expected output
    JsonObject message = messageCaptor.getValue();
    String expected = "We do not have this type of pizza.";
    assertEquals(expected, message.get("text").getAsString());
  }

  @Test
  public void testOrderIsNotRight() {
    //create a spy we want to preserve the implementation of the methods
    PizzaShopWebServer pizzaShopWebServerSpy = spy(PizzaShopWebServer.class);
    //tell the spy object to not do anything when the method sendJson is called with a Json object parameter
    doNothing().when(pizzaShopWebServerSpy).sendJson(Mockito.isA(JsonObject.class));
    //create a dummy object for the session as we need it to open the session
    Session sessionDummy = mock(Session.class);
    //open session
    pizzaShopWebServerSpy.onOpen(sessionDummy);
    //prepare input to send to the pizza shop web server
    JsonObject order = new JsonObject();
    order.addProperty("text", "The order is not right.");
    //send the input to the pizza shop
    pizzaShopWebServerSpy.onMessage(order.toString());
    //capture the json object passed to the sendJson method of the pizza shop spy
    ArgumentCaptor<JsonObject> messageCaptor = ArgumentCaptor.forClass(JsonObject.class);
    verify(pizzaShopWebServerSpy).sendJson(messageCaptor.capture());
    //check that we have the expected output
    JsonObject message = messageCaptor.getValue();
    String expected = "The order is not right.";
    assertEquals(expected, message.get("text").getAsString());
  }
}
