package com.mytectra.springboot.PizzaBunglow.web.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.mytectra.springboot.PizzaBunglow.Store.AddOnStore;
import com.mytectra.springboot.PizzaBunglow.model.AddOns;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers= {AddOnsController.class })
public class AddOnsControllerTest {

@Autowired
private MockMvc mvc;	

@MockBean
private AddOnStore addOnStore;

		@Test
		@WithMockUser(username = "jram" , authorities = {"ROLE_USER"})
		public void getAddOnsTestJson() throws Exception {
		
			AddOns addOns1= new AddOns();
			addOns1.setId(1);
			addOns1.setName("Coke");
			addOns1.setDescription("Cool drinks");
			addOns1.setCost(30);
			
			AddOns addOns2= new AddOns();
			addOns2.setId(2);
			addOns2.setName("Fries");
			addOns2.setDescription("French Fries");
			addOns2.setCost(50);
			
			AddOns addOns3= new AddOns();
			addOns3.setId(3);
			addOns3.setName("Chicken Burger");
			addOns3.setDescription("Burger with Chicken");
			addOns3.setCost(60);
			
			List<AddOns> addOnsList= new ArrayList<>();
			addOnsList.add(addOns1);
			addOnsList.add(addOns2);
			addOnsList.add(addOns3);
			
			Mockito.when(addOnStore.getAllAddOns()).thenReturn(addOnsList);
			
			mvc.perform(MockMvcRequestBuilders.get(URI.create("/addOns"))).
			andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).
			andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE)).
			andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1)).
			andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2));
		}
		
		@Test
		public void getAddOnsTestXml() throws Exception {
		
			AddOns addOns1= new AddOns();
			addOns1.setId(1);
			addOns1.setName("Coke");
			addOns1.setDescription("Cool drinks");
			addOns1.setCost(30);
			
			AddOns addOns2= new AddOns();
			addOns2.setId(2);
			addOns2.setName("Fries");
			addOns2.setDescription("French Fries");
			addOns2.setCost(50);
			
			AddOns addOns3= new AddOns();
			addOns3.setId(3);
			addOns3.setName("Chicken Burger");
			addOns3.setDescription("Burger with Chicken");
			addOns3.setCost(60);
			
			List<AddOns> addOnsList= new ArrayList<>();
			addOnsList.add(addOns1);
			addOnsList.add(addOns2);
			addOnsList.add(addOns3);
			
			Mockito.when(addOnStore.getAllAddOns()).thenReturn(addOnsList);
			
			mvc.perform(MockMvcRequestBuilders.get(URI.create("/addOns")).accept(MediaType.APPLICATION_XML_VALUE)).
			andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).
			andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_XML_VALUE)).
			andExpect(MockMvcResultMatchers.xpath("/List/item[1]/id").string("1")).
			andExpect(MockMvcResultMatchers.xpath("/List/item[2]/id").string("2"));
		}
		
		@Test
		public void addOnsSearchTestJson() throws Exception {
			
			AddOns addOns1= new AddOns();
			addOns1.setId(1);
			addOns1.setName("Coke");
			addOns1.setDescription("Cool drinks");
			addOns1.setCost(30);
			
			Mockito.when(addOnStore.getAddOnsByName(addOns1.getName())).thenReturn(addOns1);
			
			mvc.perform(MockMvcRequestBuilders.get(URI.create("/addOns/search")).param("name", addOns1.getName())).
			andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).
			andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE)).
			andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
		}
		
		@Test
		public void addOnsSearchTestXml() throws Exception {
			
			AddOns addOns1= new AddOns();
			addOns1.setId(1);
			addOns1.setName("Coke");
			addOns1.setDescription("Cool drinks");
			addOns1.setCost(30);
			
			Mockito.when(addOnStore.getAddOnsByName(addOns1.getName())).thenReturn(addOns1);
			
			mvc.perform(MockMvcRequestBuilders.get(URI.create("/addOns/search")).param("name", addOns1.getName()).accept(MediaType.APPLICATION_XML_VALUE)).
			andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).
			andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_XML_VALUE)).
			andExpect(MockMvcResultMatchers.xpath("/addons/id").string("1"));
		}
		
		@Test
		public void getAddOnsByIdJson() throws Exception {
			
			AddOns addOns1= new AddOns();
			addOns1.setId(1);
			addOns1.setName("Coke");
			addOns1.setDescription("Cool drinks");
			addOns1.setCost(30);
			
			Mockito.when(addOnStore.getAddOnsById(addOns1.getId())).thenReturn(addOns1);
			
			mvc.perform(MockMvcRequestBuilders.get(URI.create("/addOns/1"))).
			andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).
			andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE)).
			andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
		}
		
		@Test
		public void getAddOnsByIdXml() throws Exception {
			
			AddOns addOns1= new AddOns();
			addOns1.setId(1);
			addOns1.setName("Coke");
			addOns1.setDescription("Cool drinks");
			addOns1.setCost(30);
			
			Mockito.when(addOnStore.getAddOnsById(addOns1.getId())).thenReturn(addOns1);
			
			mvc.perform(MockMvcRequestBuilders.get(URI.create("/addOns/1")).accept(MediaType.APPLICATION_XML_VALUE)).
			andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).
			andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_XML_VALUE)).
			andExpect(MockMvcResultMatchers.xpath("/addons/id").string("1"));
		}
		
		@Test
		public void addAddOnsJson() throws Exception {
			
			String jsonAddons= "{" + 
					"\"id\": 1," + 
					"\"name\": \"Diet Coke\"," + 
					"\"description\": \"Diet Cool drinks\"," + 
					"\"cost\": 40" + 
					"}";
			
			mvc.perform(MockMvcRequestBuilders.post(URI.create("/addOns")).content(jsonAddons).contentType(MediaType.APPLICATION_JSON_VALUE)).
			andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).
			andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE)).
			andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS"));
		}
		
		
		
		@Test
		public void addAddOnsXml() throws Exception {
			
			String xmlAddons= "<addons><name>Coke</name><description>Cool drinks</description><cost>30</cost><addons_id>1</addons_id></addons>";
			
			mvc.perform(MockMvcRequestBuilders.post(URI.create("/addOns")).content(xmlAddons).contentType(MediaType.APPLICATION_XML_VALUE).accept(MediaType.APPLICATION_XML_VALUE)).
			andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).
			andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_XML_VALUE)).
			andExpect(MockMvcResultMatchers.xpath("/ResponseWrapper/status").string("SUCCESS"));
		}
		
		@Test
		public void updateAddOnsJson() throws Exception {
			
			String jsonAddons= "{" + 
					"\"id\": 1," + 
					"\"name\": \"Diet Coke\"," + 
					"\"description\": \"Diet Cool drinks\"," + 
					"\"cost\": 40" + 
					"}";
			
			Mockito.when(addOnStore.updateAddOnsById(Mockito.any())).thenReturn(true);
			
			mvc.perform(MockMvcRequestBuilders.put(URI.create("/addOns/1")).content(jsonAddons).contentType(MediaType.APPLICATION_JSON_VALUE)).
			andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).
			andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE)).
			andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS"));
		}
		
		@Test
		public void updateAddOnsXml() throws Exception {
			
			String xmlAddons= "<addons><name>Coke</name><description>Cool drinks</description><cost>30</cost><addons_id>1</addons_id></addons>";
			
			Mockito.when(addOnStore.updateAddOnsById(Mockito.any())).thenReturn(true);
			
			mvc.perform(MockMvcRequestBuilders.put(URI.create("/addOns/1")).content(xmlAddons).contentType(MediaType.APPLICATION_XML_VALUE).accept(MediaType.APPLICATION_XML_VALUE)).
			andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).
			andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_XML_VALUE)).
			andExpect(MockMvcResultMatchers.xpath("/ResponseWrapper/status").string("SUCCESS"));
		}
		
		@Test
		public void deleteAddOnsJson() throws Exception {
			
			Mockito.when(addOnStore.deleteAddOnsById(1)).thenReturn(true);
			
			mvc.perform(MockMvcRequestBuilders.delete(URI.create("/addOns/1"))).
			andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).
			andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE)).
			andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS"));
		}
		
		@Test
		public void deleteAddOnsXml() throws Exception {
			
			Mockito.when(addOnStore.deleteAddOnsById(1)).thenReturn(true);
			
			mvc.perform(MockMvcRequestBuilders.delete(URI.create("/addOns/1")).accept(MediaType.APPLICATION_XML_VALUE)).
			andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).
			andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_XML_VALUE)).
			andExpect(MockMvcResultMatchers.xpath("/ResponseWrapper/status").string("SUCCESS"));
		}
}
