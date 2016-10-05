package com.rainsoft.demo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MyExceptionHandle {
	
	Logger logger = LoggerFactory.getLogger(MyExceptionHandle.class);
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ResponseBody
	Map<String,String> HandleRequestMethodException(HttpRequestMethodNotSupportedException ex) {
				
		Map<String,String> result = new HashMap<>();
		StringBuilder sb = new StringBuilder();
		result.put("success", Boolean.FALSE.toString());
		sb.append(String.format("The request method [%s] is not supported.", ex.getMethod()));
		sb.append(String.format("The supported method is %s",Arrays.toString(ex.getSupportedMethods())));		
		result.put("errorMessage", sb.toString());		
		return result;
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	@ResponseBody	
	Map<String,String> HandleRedisException(RedisConnectionFailureException ex) {
		Map<String,String> result = new HashMap<>();
		StringBuilder sb = new StringBuilder();
		sb.append(ex.getMessage() + System.lineSeparator());
		sb.append(ex.getMostSpecificCause().getMessage());
		sb.append("Please contact the system admin");
		result.put("success", Boolean.FALSE.toString());		
		result.put("errorMessage", sb.toString());		
		return result;		
	}
	
	
	
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	Map<String,String> HandleUnknownException(Exception ex) {
		logger.error("User encounter server error:",ex);
		Map<String,String> result = new HashMap<>();
		StringBuilder sb = new StringBuilder();		
		result.put("success", Boolean.FALSE.toString());
		sb.append("The server encounter a error, please report to admin");
		result.put("errorMessage", sb.toString());
		return result;
	}
	
	
}
