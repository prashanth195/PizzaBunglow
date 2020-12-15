package com.mytectra.springboot.PizzaBunglow.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mytectra.springboot.PizzaBunglow.model.AddOns;

@Component
public class AddOnsDaoJdbcImpl implements AddOnsDao{
	
	@Autowired
	private JdbcTemplate template;
	
	private final String insert_q = "insert into addons values(?,?,?,?,?)";
	
	private final String getALLAddOns = "select * from addons";
	
	private final String getAddOnsByName = "select * from addons where addons_name=?";
	
	private final String getAddOnsById = "select * from addons where addons_id=?";
	
	private final String deleteAddOnsById = "delete from addons where addons_id=?";
	
	private final RowMapper mapper =  new RowMapper<AddOns>() {

		@Override
		public AddOns mapRow(ResultSet arg0, int arg1) throws SQLException {
			AddOns addOns = new AddOns();
			addOns.setId(arg0.getInt("addons_id"));
			addOns.setName(arg0.getString("addons_name"));
			addOns.setDescription(arg0.getString("addons_description"));
			addOns.setCost(arg0.getInt("addons_cost"));
			return addOns;
		}
		
	};

	@Override
	public void saveAddOns(AddOns addOns) {
		template.update(insert_q, addOns.getId() , addOns.getName() , addOns.getDescription() , new Date() ,addOns.getCost());
		
	}

	@Override
	public List<AddOns> getAllAddOns() {
		return template.query(getALLAddOns,mapper);
	}

	@Override
	public AddOns getAddOnsByName(String addOnsName) {
		return (AddOns) template.queryForObject(getAddOnsByName, new Object[] {addOnsName}, mapper);
	}

	@Override
	public AddOns getAddOnsById(int addOns_Id) {
		return (AddOns) template.queryForObject(getAddOnsById, new Object[] {addOns_Id}, mapper);
	}

	@Override
	public void deleteAddOnsById(int addOns_Id) {
		template.update(deleteAddOnsById, addOns_Id);
	}

}
