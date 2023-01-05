
package com.example.demo;


import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
	public Float rollingAverage(@RequestBody Map<String, Integer>[] jsonData) {

		TimeData timeData = new TimeData(jsonData);
		return timeData.GetRollingAverage();
	}

}
