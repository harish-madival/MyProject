package com.hotel.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerImpl {
	
	@ExceptionHandler(value = NullPointerException.class)	
	public String exceptionHandlerNull(Model m) {
		m.addAttribute("exception","null pointer exception");
		return "";
	}
}
