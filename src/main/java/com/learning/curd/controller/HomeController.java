package com.learning.curd.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/home")
	public ResponseEntity<String> welComePage(){
		return ResponseEntity.status(HttpStatus.OK)
				.body("welcome to my website");
	}
}
