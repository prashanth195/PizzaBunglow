package com.mytectra.springboot.PizzaBunglow.Security.Dao;

//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;

import com.mytectra.springboot.PizzaBunglow.Security.Entity.PBUser;

/*@Repository
public interface PBUserDao extends CrudRepository<PBUser, Integer> {

	public PBUser getPBUserByName(String name);
	
}*/


public interface PBUserDao{
	
	void savePBUser(PBUser user);
	
	PBUser getPBUserByName(String username);
	
	
}
