package com.mytectra.springboot.PizzaBunglow.Dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.mytectra.springboot.PizzaBunglow.model.Pizza;

@Repository
public interface PizzaPageRepository extends PagingAndSortingRepository<Pizza, Integer>{

	
	
}
