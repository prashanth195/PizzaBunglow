package com.mytectra.springboot.PizzaBunglow.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true , jsr250Enabled = true , prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userServ;	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		/*auth.inMemoryAuthentication()
		.withUser("sachin").password(encoder.encode("root123")).authorities("ADMIN")
		.and()
		.withUser("raju").password(encoder.encode("123456")).authorities("USER");*/
		
		auth.userDetailsService(userServ);
		
		
		
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
 	
	
	protected void configure(HttpSecurity http) {
		// Always put basic authentication (.httpBasic) at the end, as we are using basic authentication over here.
		// Otherwise, application will consider the web authentication (username & password).
		try {
			http
				//.authorizeRequests()
				//.antMatchers("/*addOns/**")
				//.hasAnyAuthority("ADMIN")
			//.and()
				.authorizeRequests()
				.antMatchers("/**")
				.authenticated()
			.and()
				.httpBasic()
			.and()
				.csrf()
				.disable();
			
			//disabled csrf due to postman not getting the X_CSRF token cookie
			//http.csrf().disable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}