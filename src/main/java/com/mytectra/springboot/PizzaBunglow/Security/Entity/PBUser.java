package com.mytectra.springboot.PizzaBunglow.Security.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table
@NamedQuery(name = "getPBUserByName" , query = "FROM PBUser p where p.username= ?1")
public class PBUser implements UserDetails {

	@Id
	@GeneratedValue
	public int id;
	
	@Column
	public String dbAuthorities;
	
	@Column
	public String password;
	
	@Column
	public String username;
	
	@Column
	public boolean isAccountNonExpired;

	@Column
	public boolean isAccountNonLocked;
	
	@Column
	public boolean isCredentialsNonExpired;

	@Column
	public boolean isEnabled;
	
	
	public enum Roles implements GrantedAuthority {
		
		ROLE_USER,ROLE_ADMIN;

		@Override
		public String getAuthority() {
			return this.name();
		}
		
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(dbAuthorities != null && !dbAuthorities.isEmpty()) {
			String[] authoritesArray = dbAuthorities.split(",");
			List<Roles> roles = new ArrayList<PBUser.Roles>();
			for(String authority : authoritesArray) {
				roles.add(Roles.valueOf(authority));
			}
			return roles;
		}
		return null;
	}

	@Override
	public String getPassword() {
		
		return this.password;
	}

	@Override
	public String getUsername() {
		
		return this.username;
	}

	
	
	@Override
	public boolean isAccountNonExpired() {
		
		return this.isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return this.isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return this.isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		
		return this.isEnabled;
	}

	public String getDbAuthorities() {
		return dbAuthorities;
	}

	public void setDbAuthorities(String dbAuthorities) {
		this.dbAuthorities = dbAuthorities;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setAccountNonExpired(boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}

	public void setAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}

	public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	
}
