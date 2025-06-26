package com.learning.curd.controller.advice;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class EmployeeErrorController implements ErrorController {

	private static final String path = "/error";

	@Autowired
	private ErrorAttributes attributes;

	@RequestMapping(path)
	public ResponseEntity<Map<String, Object>> handleError(HttpServletRequest request) {
		WebRequest webRequest = new ServletWebRequest(request);

		Map<String, Object> errorDetails = attributes.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());

		int status = (int) errorDetails.getOrDefault("status", 500);

		return ResponseEntity.status(status).body(errorDetails);
	}
}
