package com.mytectra.springboot.PizzaBunglow.Security.Dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mytectra.springboot.PizzaBunglow.Security.Entity.PBUser;

@Transactional
@Component
public class PBDaoHibernateImpl implements PBUserDao{

	@Autowired
	EntityManager manager;
	
	@Override
	public void savePBUser(PBUser user) {
		manager.persist(user);
		
	}

	@Override
	public PBUser getPBUserByName(String username) {
		Query query = manager.createNamedQuery("getPBUserByName");
		query.setParameter(1, username);
		return (PBUser) query.getSingleResult();
		
	}

}
