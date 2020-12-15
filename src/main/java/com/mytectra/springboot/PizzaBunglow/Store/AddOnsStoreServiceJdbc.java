package com.mytectra.springboot.PizzaBunglow.Store;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.mytectra.springboot.PizzaBunglow.Dao.AddOnsDao;
import com.mytectra.springboot.PizzaBunglow.model.AddOns;


@Component
@Primary
public class AddOnsStoreServiceJdbc implements AddOnStore{

	@Autowired
	private AddOnsDao addOnsDao;
	
	@Override
	public void addAddOns(AddOns addOns) {
		// TODO Auto-generated method stub
		if(addOns!=null && 
				addOns.getName()!=null && !addOns.getName().trim().isEmpty() &&
						  addOns.getCost()!=0 && 
								addOns.getDescription()!=null && !addOns.getDescription().trim().isEmpty()){
		
		addOnsDao.saveAddOns(addOns);
	}
	}
	@Override
	public List<AddOns> getAllAddOns() {
		// TODO Auto-generated method stub
		return addOnsDao.getAllAddOns();
	}

	@Override
	public AddOns getAddOnsByName(String addOnsName) throws AddOnsNotFoundException {
		// TODO Auto-generated method stub
		if(addOnsName!=null && !addOnsName.trim().isEmpty()) {
			return addOnsDao.getAddOnsByName(addOnsName);
		}
		throw new AddOnsNotFoundException();
	}
	
	@Override
	public AddOns getAddOnsById(int id) throws AddOnsNotFoundException {
		// TODO Auto-generated method stub
		if(id > 0) {
		return addOnsDao.getAddOnsById(id);
		}
		throw new AddOnsNotFoundException();
	}
	@Override
	public boolean updateAddOnsById(AddOns addOns) throws AddOnsNotFoundException {
		// TODO Auto-generated method stub
		AddOns addOns1=getAddOnsById(addOns.getId());
		if(addOns1!=null) 
		{
		addOnsDao.deleteAddOnsById(addOns1.getId());
		addOnsDao.saveAddOns(addOns);
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
		addOnsDao.deleteAddOnsById(id);
		return true;
		}
		return false;
	}

	
}
