
package com.example.demo;


import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RollingAverageController {

	public static void main(String[] args) {
		SpringApplication.run(RollingAverageController.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@PostMapping("/rolling-average")
	public ResponseEntity rollingAverage(@RequestBody Map<String, Integer>[] jsonData) {


		// checking timestamp interval regurality

		TimeData timeData = new TimeData(jsonData);
		boolean valid = timeData.checkValidity();
		if(valid==false) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		return ResponseEntity.ok(timeData.getRollingAverage());
	}

}
