package com.mytectra.springboot.PizzaBunglow.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.mytectra.springboot.PizzaBunglow.model.AddOns;



//@Component
//@Primary
public class AddOnsDaoMongoImpl implements AddOnsDao{
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public void saveAddOns(AddOns addons) {
		// TODO Auto-generated method stub
		mongoTemplate.insert(addons);
	}

	@Override
	public List<AddOns> getAllAddOns() {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(AddOns.class);
	}

	@Override
	public AddOns getAddOnsByName(String addOnsName) {
		// TODO Auto-generated method stub
		return mongoTemplate.findOne(Query.query(Criteria.where("name").in(addOnsName)), AddOns.class);
	}

	@Override
	public AddOns getAddOnsById(int addOns_Id) {
		// TODO Auto-generated method stub
		//return mongoTemplate.findOne(Query.query(Criteria.where("_id").in(pizza_id)), Pizza.class);
		return mongoTemplate.findById(addOns_Id, AddOns.class);
	}

	@Override
	public void deleteAddOnsById(int addOns_Id) {
		// TODO Auto-generated method stub
		mongoTemplate.remove(Query.query(Criteria.where("_id").in(addOns_Id)), AddOns.class);
	}

	
	
}
