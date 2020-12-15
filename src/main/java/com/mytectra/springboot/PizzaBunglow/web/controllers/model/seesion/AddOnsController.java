/*package com.mytectra.springboot.PizzaBunglow.web.controllers.model.seesion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mytectra.springboot.PizzaBunglow.Store.AddOnStore;
import com.mytectra.springboot.PizzaBunglow.model.AddOns;
import com.mytectra.springboot.PizzaBunglow.model.Pizza;
import com.mytectra.springboot.PizzaBunglow.web.controllers.model.AddOnsClient;
import com.mytectra.springboot.PizzaBunglow.web.controllers.model.RequestScopeBean;
import com.mytectra.springboot.PizzaBunglow.web.controllers.model.seesion.SessionScopeBean;

@RestController
public class AddOnsController {

	@Autowired
	private AddOnStore addOnsStore;
	
	@Autowired
	private AddOnsClient clientBean;
	
	//Session Bean
	@Autowired
	private SessionScopeBean scope;
	
	@Autowired
	private RequestScopeBean req;
	
	//@GetMapping("/addOns")
	@RequestMapping("/addOns")
 	public @ResponseBody List<AddOns> getAddOns(){
		scope.count();
		System.out.println("Sesseion Count is " + scope.getCount());
		
		req.count();
		System.out.println("Request Count is " + req.getCount());
 		return addOnsStore.getAllAddOns();
 	}
	
	@GetMapping("/addOns/search")
 	public AddOns getAddOn(@RequestParam (name="name") String name){
		AddOns addOn= addOnsStore.getAddOnsByName(name);
		return addOn;
 	}
	
	@PostMapping("/addOns")
	public String AddaddOns(@RequestBody AddOns addOns) {
		addOnsStore.addAddOns(addOns);
		return "Success";
	}

	@GetMapping("/addOns/{id}")
	public AddOns getAddOns(@PathVariable("id") Integer id, @RequestHeader(name = "client" ,required = false) String client) {
		System.out.println("Client is "+client);
		return addOnsStore.getAddOnsById(id);
	}
	
	@PutMapping("/addOns/{id}")
	public String updateAddOns(@PathVariable("id") Integer id, @RequestBody AddOns addOns, 
			@RequestHeader(name = "clientName" ,required = false) String client,
			@RequestHeader(name = "clientId" ,required = false) String clientId) {
		addOns.setId(id);
		clientBean.setClientName(client);
		clientBean.setClientId(clientId);
		if(addOnsStore.updateAddOnsById(addOns)) {
			return "Success"+clientBean.getClientId()+"==="+clientBean.getClientName();
		}else {
			return "Failure";
		}
	}
	
	@DeleteMapping("/addOns/{id}")
	public String deleteAddOns(@PathVariable("id") Integer id) {
		if(addOnsStore.deleteAddOnsById(id)) {
			return "Success";
		}else {
			return "Failure";
		}
	}
}
*/