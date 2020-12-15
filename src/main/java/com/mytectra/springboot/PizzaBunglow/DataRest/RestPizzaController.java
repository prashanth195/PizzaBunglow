package com.mytectra.springboot.PizzaBunglow.DataRest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.mytectra.springboot.PizzaBunglow.model.Pizza;

@RepositoryRestResource(collectionResourceRel="pizza" , path = "pizza-datarest" )
public interface RestPizzaController extends PagingAndSortingRepository<Pizza, Integer> {

	
}
