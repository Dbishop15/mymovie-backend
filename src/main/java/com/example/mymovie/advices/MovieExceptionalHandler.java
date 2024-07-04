package com.example.mymovie.advices;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.mymovie.exceptions.MovieAlreadyExistsException;
import com.example.mymovie.exceptions.MovieNotFoundException;

@RestControllerAdvice
public class MovieExceptionalHandler {
	 @ResponseStatus(HttpStatus.NOT_FOUND)
	    @ExceptionHandler(value = {MovieNotFoundException.class})
	    public ResponseEntity<Object> handleProductNotFoundException(MovieNotFoundException movieNotFoundException){

	        Map<String, String> errorMap= new HashMap<>();

	        errorMap.put("message", movieNotFoundException.getMessage());
	        errorMap.put("timestamp", new Date(0).toString());
	        errorMap.put("httpStatus", HttpStatus.NOT_FOUND.toString());

	        return new ResponseEntity<>(errorMap, HttpStatus.NOT_FOUND);
	 }
	 
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
	    public ResponseEntity<Object>  handleMethodArgumentNotValidException(MethodArgumentNotValidException  methodArgumentNotValidException){
	        Map<String, String> errorMap= new HashMap<>();

	       methodArgumentNotValidException.getFieldErrors().forEach(error ->{
	           errorMap.put(error.getField(), error.getDefaultMessage());
	       });

	        errorMap.put("timestamp", new Date(0).toString());
	        errorMap.put("httpStatus", HttpStatus.BAD_REQUEST.toString());

	        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
	    }
	 
	 @ResponseStatus(HttpStatus.CONFLICT)
	 @ExceptionHandler(value = {MovieAlreadyExistsException.class})
	    public ResponseEntity<Object> handleMovieAlreadyExistsException(MovieAlreadyExistsException movieAlreadyExistsException) {
	       
	        Map<String, String> errorMap= new HashMap<>();

	        errorMap.put("message", movieAlreadyExistsException.getMessage());
	        errorMap.put("timestamp", new Date(0).toString());
	        errorMap.put("httpStatus", HttpStatus.CONFLICT.toString());

	        return new ResponseEntity<>(errorMap, HttpStatus.CONFLICT);
	 }
}
