package dev.diogoro.lyncastest;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LyncasTestApplication {

	@PostConstruct
    void started() {
      TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
    }
	
	public static void main(String[] args) {
		SpringApplication.run(LyncasTestApplication.class, args);
	}

}
