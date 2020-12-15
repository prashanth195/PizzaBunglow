package com.mytectra.springboot.PizzaBunglow.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mytectra.springboot.PizzaBunglow.model.Pizza;

@Component
public class PizzaDaoJdbcImpl implements PizzaDao {
	
	@Autowired
	private JdbcTemplate template;
	
	private final String insert_q = "insert into pizza values(?,?,?,?,?)";
	
	private final String getALLPizzas = "select * from pizza";
	
	private final String getPizzaByName = "select * from pizza where pizza_name=?";
	
	private final String getPizzaById = "select * from pizza where pizza_id=?";
	
	private final String deletePizzaById = "delete from pizza where pizza_id=?";
	
	private final RowMapper mapper =  new RowMapper<Pizza>() {

		@Override
		public Pizza mapRow(ResultSet arg0, int arg1) throws SQLException {
			Pizza pizza = new Pizza();
			pizza.setId((arg0.getInt("pizza_id")));
			pizza.setName(arg0.getString("pizza_name"));
			pizza.setDescription(arg0.getString("pizza_description"));
			pizza.setCost(arg0.getInt("pizza_cost"));
			return pizza;
		}
		
	};


	@Override
	public void save(Pizza pizza) {
		template.update(insert_q, pizza.getId() , pizza.getName() , pizza.getDescription() , new Date() ,pizza.getCost());
	}

	@Override
	public List<Pizza> getAllPizzas() {
		return template.query(getALLPizzas,mapper);
	}

	@Override
	public Pizza getPizzaByName(String pizzaName) {
		return (Pizza) template.queryForObject(getPizzaByName, new Object[] {pizzaName}, mapper);
	}

	@Override
	public Pizza getPizzaById(int pizza_id) {
		// TODO Auto-generated method stub
		return (Pizza) template.queryForObject(getPizzaById, new Object[] {pizza_id}, mapper);
	}

	@Override
	public void deletePizzaById(int pizza_id) {
		template.update(deletePizzaById, pizza_id);
		
	}

	
	

}


