package com.ayoungmk.orders_sibs.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ayoungmk.orders_sibs.model.ApiError;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { ItensNotFoundException.class})
	protected ResponseEntity<Object> itensHandleConflict(RuntimeException ex, WebRequest request) {
		ApiError bodyOfResponse = new ApiError();
		bodyOfResponse.setMessage(ex.getMessage());
		bodyOfResponse.setStatus(HttpStatus.NOT_FOUND);
		bodyOfResponse.setError("404");
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(value = { OrdersNotFoundException.class})
	protected ResponseEntity<Object> ordersHandleConflict(RuntimeException ex, WebRequest request) {
		ApiError bodyOfResponse = new ApiError();
		bodyOfResponse.setMessage(ex.getMessage());
		bodyOfResponse.setStatus(HttpStatus.NOT_FOUND);
		bodyOfResponse.setError("404");
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(value = { StockNotFoundException.class})
	protected ResponseEntity<Object> stockHandleConflict(RuntimeException ex, WebRequest request) {
		ApiError bodyOfResponse = new ApiError();
		bodyOfResponse.setMessage(ex.getMessage());
		bodyOfResponse.setStatus(HttpStatus.NOT_FOUND);
		bodyOfResponse.setError("404");
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(value = { StockMovementsNotFoundException.class})
	protected ResponseEntity<Object> stockMovementsHandleConflict(RuntimeException ex, WebRequest request) {
		ApiError bodyOfResponse = new ApiError();
		bodyOfResponse.setMessage(ex.getMessage());
		bodyOfResponse.setStatus(HttpStatus.NOT_FOUND);
		bodyOfResponse.setError("404");
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(value = { UsersNotFoundException.class})
	protected ResponseEntity<Object> usersHandleConflict(RuntimeException ex, WebRequest request) {
		ApiError bodyOfResponse = new ApiError();
		bodyOfResponse.setMessage(ex.getMessage());
		bodyOfResponse.setStatus(HttpStatus.NOT_FOUND);
		bodyOfResponse.setError("404");
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	
}