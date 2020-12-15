package com.mytectra.springboot.PizzaBunglow.IntegrationTest;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.junit.jupiter.api.Test;

public class PizzaIntergrationTest {

	//@Test
	public void  testIntegartion() throws IOException {
		URL url = new URL("http://localhost:8080/pizzas/1" );
		
		InputStream is = url.openConnection().getInputStream();
		
		
		int i = 0;
		while((i =  is.read()) != -1 ) {
			System.out.print((char)i);
		}
		
	}

}
