
package com.example.demo;


import java.util.Map;

import javax.validation.Valid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.TimeDataDto;


@SpringBootApplication
@RestController
public class RollingAverageController {

	public static void main(String[] args) {
		SpringApplication.run(RollingAverageController.class, args);
	}


	@PostMapping("/rolling-average")
	public ResponseEntity rollingAverage(@Valid @RequestBody Map<String, Integer>[] jsonData) {


		
		TimeDataDto timeData = new TimeDataDto(jsonData);

		// checking timestamp interval regurality
		boolean valid = timeData.checkValidity();
		if(valid==false) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
				"error: moving average cannot be calculated due to irregular intervals between the received timestamps");
		}
		
		
		return ResponseEntity.ok(timeData.getRollingAverage());
	}

	

}
