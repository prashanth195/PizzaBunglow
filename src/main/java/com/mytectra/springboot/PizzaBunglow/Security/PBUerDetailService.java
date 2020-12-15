package com.mytectra.springboot.PizzaBunglow.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.mytectra.springboot.PizzaBunglow.Security.Dao.PBUserDao;
import com.mytectra.springboot.PizzaBunglow.Security.Entity.PBUser;

@Component
public class PBUerDetailService implements UserDetailsService {

	@Autowired
	private PBUserDao dao;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		PBUser  user = dao.getPBUserByName(username);
		user.setPassword(encoder.encode(user.getPassword()));
		return user;
	}

}
