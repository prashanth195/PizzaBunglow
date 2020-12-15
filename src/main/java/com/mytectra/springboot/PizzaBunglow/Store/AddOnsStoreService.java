package com.mytectra.springboot.PizzaBunglow.Store;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mytectra.springboot.PizzaBunglow.model.AddOns;
@Component
public class AddOnsStoreService implements AddOnStore{

	private List<AddOns> addOnsList= new ArrayList<AddOns>();
	@Override
	public void addAddOns(AddOns addOns) {
		// TODO Auto-generated method stub
		if(addOns!=null && 
				addOns.getName()!=null && !addOns.getName().trim().isEmpty() &&
						addOns.getId()!=0 && addOns.getCost()!=0 && 
								addOns.getDescription()!=null && !addOns.getDescription().trim().isEmpty()){
		
		addOnsList.add(addOns);
	}
	}
	@Override
	public List<AddOns> getAllAddOns() {
		// TODO Auto-generated method stub
		return addOnsList;
	}

	@Override
	public AddOns getAddOnsByName(String addOnsName) throws AddOnsNotFoundException {
		// TODO Auto-generated method stub
		if(addOnsName!=null && !addOnsName.trim().isEmpty()
				&& addOnsList!=null) {
		for(AddOns addOns: addOnsList) {
			if(addOns!=null && addOns.getName()!=null
				&& !addOns.getName().trim().isEmpty() && 
				addOns.getName().equalsIgnoreCase(addOnsName)) {
				return addOns;
			}
		}
		}
		throw new AddOnsNotFoundException();
	}
	
	@Override
	public AddOns getAddOnsById(int id) throws AddOnsNotFoundException {
		// TODO Auto-generated method stub
		if(id > 0
				&& addOnsList!=null) {
		for(AddOns addOns: addOnsList) {
			if(addOns!=null && id==addOns.getId()) {
				return addOns;
			}
		}
		}
		throw new AddOnsNotFoundException();
	}
	@Override
	public boolean updateAddOnsById(AddOns addOns) throws AddOnsNotFoundException {
		// TODO Auto-generated method stub
		AddOns addOns1=getAddOnsById(addOns.getId());
		if(addOns1!=null) 
		{
		addOnsList.remove(addOns1);
		addOnsList.add(addOns);
		return true;
		}
		return false;
	}
	@Override
	public boolean deleteAddOnsById(int id) throws AddOnsNotFoundException {
		// TODO Auto-generated method stub
		AddOns addOns=getAddOnsById(id);
		if(addOns!=null) 
		{
		addOnsList.remove(addOns);
		return true;
		}
		return false;
	}

	
}
