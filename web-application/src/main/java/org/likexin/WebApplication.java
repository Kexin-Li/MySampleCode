package org.likexin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations={"classpath:application-spring.xml"})
public class WebApplication {

	public static void main(String [] args) throws Exception {
		SpringApplication.run(WebApplication.class,args);
	}
	
}
