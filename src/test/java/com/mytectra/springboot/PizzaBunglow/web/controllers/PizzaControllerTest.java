package com.mytectra.springboot.PizzaBunglow.web.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.verification.VerificationMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.mytectra.springboot.PizzaBunglow.Store.PizzaStore;
import com.mytectra.springboot.PizzaBunglow.model.Pizza;
import com.mytectra.springboot.PizzaBunglow.web.controllers.model.RequestScopeBean;

@ExtendWith({SpringExtension.class})
@WebMvcTest(controllers = {PizzaController.class})
public class PizzaControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private PizzaStore pizzaStore;
	
	@MockBean
	private RequestScopeBean bean;
	
	
	
	@Test
	public void testGetPizzasJson() throws Exception {
		

		Pizza pizza= new Pizza();
		pizza.setId(1);
		pizza.setName("Panner Pizza");
		pizza.setCost(300);
		pizza.setDescription("topped with panner");
		
		Pizza pizza1= new Pizza();
		pizza1.setId(2);
		pizza1.setName("Chicken Pizza");
		pizza1.setCost(450);
		pizza1.setDescription("topped with chicken");
		
		List<Pizza> pizzaList= new ArrayList<>();
		pizzaList.add(pizza);
		pizzaList.add(pizza1);
		Mockito.when(pizzaStore.getAllPizzas()).thenReturn(pizzaList);
		
		mvc.perform(MockMvcRequestBuilders.get(URI.create("/pizzas")).header("Page", 0))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2));

		
	}

	
	@Test
	public void testGetPizzasXML() throws Exception {
		

		Pizza pizza= new Pizza();
		pizza.setId(1);
		pizza.setName("Panner Pizza");
		pizza.setCost(300);
		pizza.setDescription("topped with panner");
		
		Pizza pizza1= new Pizza();
		pizza1.setId(2);
		pizza1.setName("Chicken Pizza");
		pizza1.setCost(450);
		pizza1.setDescription("topped with chicken");
		
		List<Pizza> pizzaList= new ArrayList<>();
		pizzaList.add(pizza);
		pizzaList.add(pizza1);
		Mockito.when(pizzaStore.getAllPizzas()).thenReturn(pizzaList);
		
		mvc.perform(MockMvcRequestBuilders.get(URI.create("/pizzas")).accept(MediaType.APPLICATION_XML_VALUE).header("Page", 0))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_XML_VALUE))
		.andExpect(MockMvcResultMatchers.xpath("/List/item[1]/id").string("1"));

		
	}
	

	@Test
	public void testGetPizzasSearch() throws Exception {
		

		Pizza pizza= new Pizza();
		pizza.setId(1);
		pizza.setName("Panner Pizza");
		pizza.setCost(300);
		pizza.setDescription("topped with panner");
		
		Mockito.when(pizzaStore.getPizzaByName(pizza.getName())).thenReturn(pizza);
		
		mvc.perform(MockMvcRequestBuilders.get(URI.create("/pizzas/search")).param("name", pizza.getName()))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(pizza.getId()));

		
	}
	
	@Test
	public void testGetPizzasSearchXML() throws Exception {
		

		Pizza pizza= new Pizza();
		pizza.setId(1);
		pizza.setName("Panner Pizza");
		pizza.setCost(300);
		pizza.setDescription("topped with panner");
		
		Mockito.when(pizzaStore.getPizzaByName(pizza.getName())).thenReturn(pizza);
		
		mvc.perform(MockMvcRequestBuilders.get(URI.create("/pizzas/search")).param("name", pizza.getName()).accept(MediaType.APPLICATION_XML_VALUE))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_XML_VALUE))
		.andExpect(MockMvcResultMatchers.xpath("/pizza/id").string("1"));

		
	}
	
	@Test
	public void testGetPizzasInsert() throws Exception {
		
		
		String jsonPizza = "{"
				 + "\"id\"  : 1,"
				+ " \"name\": \"Panner Pizza Hot\","
				+ "\"description\": \"topped with panner spicy\","
				+ " \"cost\": 300"
				+ "}";

		
		mvc.perform(MockMvcRequestBuilders.post(URI.create("/pizzas")).content(jsonPizza).contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS"));
		
		Mockito.verify(pizzaStore, Mockito.times(1)).addPizza(Mockito.any(Pizza.class));

		
	}
	
	@Test
	public void testGetPizzasInsertXml() throws Exception {
		
		
		String xmlPizza = "<pizza><name>Panner Pizza</name><description>topped with panner</description><cost>300</cost><id>1</id></pizza>";

		
		mvc.perform(MockMvcRequestBuilders.post(URI.create("/pizzas")).content(xmlPizza).contentType(MediaType.APPLICATION_XML_VALUE).accept(MediaType.APPLICATION_XML_VALUE))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_XML_VALUE))
		.andExpect(MockMvcResultMatchers.xpath("/ResponseWrapper/status").string("SUCCESS"));
		
		Mockito.verify(pizzaStore, Mockito.times(1)).addPizza(Mockito.any(Pizza.class));

		
	}
	
	
	
	@Test
	public void testListPizzasInsert() throws Exception {
		
		
		String jsonPizza = "[" 
				+  "{"
				+ "\"id\"  : 1,"
				+ " \"name\": \"Panner Pizza Hot\","
				+ "\"description\": \"topped with panner spicy\","
				+ " \"cost\": 300"
				+ "}"
				+ "," 
				+  "{"
			    + "\"id\"  : 2,"
				+ " \"name\": \"Chicken Pizza Hot\","
				+ "\"description\": \"topped with chicken spicy\","
				+ " \"cost\": 400"
				+ "}"
				+ "]";

		
		
		mvc.perform(MockMvcRequestBuilders.post(URI.create("/pizzas")).content(jsonPizza).contentType(MediaType.APPLICATION_JSON_VALUE).header("List","123"))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS"));
		
		Mockito.verify(pizzaStore, Mockito.times(1)).addPizzaList(Mockito.any());

		
	}
	
		
	@Test
	public void testListPizzasInsertXml() throws Exception {
		
		
		String xmlPizzaList = "<List><item><name>Panner Pizza</name><description>topped with panner</description><cost>300</cost><id>1</id></item>"
				+"<item><name>Chicken Pizza</name><description>topped with chicken</description><cost>450</cost><id>2</id></item></List>";
	
		mvc.perform(MockMvcRequestBuilders.post(URI.create("/pizzas")).content(xmlPizzaList).contentType(MediaType.APPLICATION_XML_VALUE).header("List","123").accept(MediaType.APPLICATION_XML_VALUE))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_XML_VALUE))
		.andExpect(MockMvcResultMatchers.xpath("/ResponseWrapper/status").string("SUCCESS"));
		
		Mockito.verify(pizzaStore, Mockito.times(1)).addPizzaList(Mockito.any());

		
	}
	
	@Test
	public void testGetPizzaById() throws Exception {
		

		Pizza pizza= new Pizza();
		pizza.setId(1);
		pizza.setName("Panner Pizza");
		pizza.setCost(300);
		pizza.setDescription("topped with panner");
		
		Mockito.when(pizzaStore.getPizzaById(pizza.getId())).thenReturn(pizza);
		
		mvc.perform(MockMvcRequestBuilders.get(URI.create("/pizzas/1")))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(pizza.getId()));

		
	}
	
	@Test
	public void testGetPizzaByIdXml() throws Exception {
		

		Pizza pizza= new Pizza();
		pizza.setId(1);
		pizza.setName("Panner Pizza");
		pizza.setCost(300);
		pizza.setDescription("topped with panner");
		
		Mockito.when(pizzaStore.getPizzaById(pizza.getId())).thenReturn(pizza);
		
		mvc.perform(MockMvcRequestBuilders.get(URI.create("/pizzas/1")).accept(MediaType.APPLICATION_XML_VALUE))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_XML_VALUE))
		.andExpect(MockMvcResultMatchers.xpath("/pizza/id").string("1"));

		
	}
	
	@Test
	public void testUpdatePizzaById() throws Exception {
		
		String jsonPizza = "{"
				+ "\"id\"  : 1,"
				+ " \"name\": \"Pan Pizza Hot\","
				+ "\"description\": \"topped with pan spicy\","
				+ " \"cost\": 300"
				+ "}";
		
		Pizza pizza= new Pizza();
		pizza.setId(1);
		pizza.setName("Panner Pizza");
		pizza.setCost(300);
		pizza.setDescription("topped with panner");
		
		Mockito.when(pizzaStore.getPizzaById(pizza.getId())).thenReturn(pizza);
		
		mvc.perform(MockMvcRequestBuilders.put(URI.create("/pizzas/1")).content(jsonPizza).contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS"));
		
	}
	
	@Test
	public void testUpdatePizzaByIdXml() throws Exception {
		
		String xmlPizza = "<pizza><name>Panner Pizza</name><description>topped with panner</description><cost>300</cost><id>1</id></pizza>";
		
		Pizza pizza= new Pizza();
		pizza.setId(1);
		pizza.setName("Panner Pizza");
		pizza.setCost(300);
		pizza.setDescription("topped with panner");
		
		Mockito.when(pizzaStore.getPizzaById(pizza.getId())).thenReturn(pizza);
		
		mvc.perform(MockMvcRequestBuilders.put(URI.create("/pizzas/1")).content(xmlPizza).contentType(MediaType.APPLICATION_XML_VALUE).accept(MediaType.APPLICATION_XML_VALUE))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_XML_VALUE))
		.andExpect(MockMvcResultMatchers.xpath("/ResponseWrapper/status").string("SUCCESS"));
		
	}
	
	@Test
	public void testDeletePizzaById() throws Exception {
		
		Pizza pizza= new Pizza();
		pizza.setId(1);
		pizza.setName("Panner Pizza");
		pizza.setCost(300);
		pizza.setDescription("topped with panner");
		
		Mockito.when(pizzaStore.getPizzaById(pizza.getId())).thenReturn(pizza);
		
		mvc.perform(MockMvcRequestBuilders.delete(URI.create("/pizzas/1")))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS"));

		
	}
	
	@Test
	public void testDeletePizzaByIdXml() throws Exception {
		
		
		Pizza pizza= new Pizza();
		pizza.setId(1);
		pizza.setName("Panner Pizza");
		pizza.setCost(300);
		pizza.setDescription("topped with panner");
		
		Mockito.when(pizzaStore.getPizzaById(pizza.getId())).thenReturn(pizza);
		
		mvc.perform(MockMvcRequestBuilders.delete(URI.create("/pizzas/1")).accept(MediaType.APPLICATION_XML_VALUE))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_XML_VALUE))
		.andExpect(MockMvcResultMatchers.xpath("/ResponseWrapper/status").string("SUCCESS"));
		
	}

}
