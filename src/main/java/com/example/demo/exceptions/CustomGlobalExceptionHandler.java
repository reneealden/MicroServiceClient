package com.example.demo.exceptions;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.constants.ErrorCode;
import com.example.demo.constants.ErrorMessage; 

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	private Logger log = LoggerFactory.getLogger(CustomGlobalExceptionHandler.class);
	
	@ExceptionHandler(IncorrectFieldFormatException.class)
    public ResponseEntity<ErrorResponse> customHandleIncorrectFieldFormat(Exception ex, WebRequest request) {
    	ErrorResponse error = new ErrorResponse();
    	error.setCode(ErrorCode.INCORRECTFIELDFORMAT);
    	error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    } 
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> otherExceptions(Exception ex, WebRequest request) {
    	StackTraceElement[] trace = ex.getStackTrace();
    	log.info("CustomGlobalException: " + ex.getMessage());
    	log.info("Class Exception: " + trace[0].getClassName());
    	
    	ErrorResponse error = new ErrorResponse();
    	error.setCode(ErrorCode.INTERNALSERVERERROR);
    	error.setMessage(ErrorMessage.INTERNALSERVERERROR);    	
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@Override
    protected ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) 
    {
    	ErrorResponse error = new ErrorResponse();
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String[] arrayError = new String[0];        
        if (fieldError != null && fieldError.getDefaultMessage() != null) {
        	arrayError = !fieldError.getDefaultMessage().isEmpty() ? fieldError.getDefaultMessage().split("-") : new String[0];
        }
        
        if (arrayError.length > 1) {
        	error.setCode(arrayError[0]);
            error.setMessage(arrayError[1]);
        }
        else {
        	error.setCode(fieldError != null ? ErrorCode.INCORRECTFIELDFORMAT : ErrorCode.INTERNALSERVERERROR);
            error.setMessage(fieldError != null ? fieldError.getDefaultMessage() : ErrorMessage.INTERNALSERVERERROR);
        }
        
        return new ResponseEntity<>(error, (fieldError != null ? HttpStatus.BAD_REQUEST : HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
