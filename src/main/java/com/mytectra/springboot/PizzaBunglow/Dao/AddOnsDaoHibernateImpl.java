package com.mytectra.springboot.PizzaBunglow.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.mytectra.springboot.PizzaBunglow.aspect.Timer;
import com.mytectra.springboot.PizzaBunglow.model.AddOns;
import com.mytectra.springboot.PizzaBunglow.model.Pizza;


//
@Transactional
@Component
@Primary
public class AddOnsDaoHibernateImpl implements AddOnsDao{
	
	@Autowired
	EntityManager manager;

	@Timer
	@Override
	public void saveAddOns(AddOns addons) {
		// TODO Auto-generated method stub
		manager.persist(addons);
	}

	@Override
	public List<AddOns> getAllAddOns() {
		
		Query query = manager.createQuery("FROM AddOns");
		return query.getResultList();
	}

	@Override
	public AddOns getAddOnsByName(String addOnsName) {

		Query query = manager.createNamedQuery("getAddOnsByName");
		query.setParameter(1, addOnsName);
		return (AddOns) query.getSingleResult();
	}

	@Override
	public AddOns getAddOnsById(int addOns_Id) {
		 
		return manager.find(AddOns.class, addOns_Id);
		
	}

	@Override
	public void deleteAddOnsById(int addOns_Id) {
		// TODO Auto-generated method stub
		manager.remove(manager.find(AddOns.class, addOns_Id));
	}

	
	
}
