package com.mytectra.springboot.PizzaBunglow.Dao;

import java.util.List;

import com.mytectra.springboot.PizzaBunglow.model.AddOns;

public interface AddOnsDao {

	void saveAddOns(AddOns addOns);
	
	List<AddOns> getAllAddOns();
	
	AddOns getAddOnsByName(String addOnsName);
	
	AddOns getAddOnsById(int addOns_Id);
	
	void deleteAddOnsById(int addOns_Id);
}
