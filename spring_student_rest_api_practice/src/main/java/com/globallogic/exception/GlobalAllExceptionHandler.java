package com.globallogic.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.globallogic.utils.ErrorDetails;

/*
 * To return error in understandable from whenever exception occurs in entire application
 * */

@ControllerAdvice
public class GlobalAllExceptionHandler extends ResponseEntityExceptionHandler {

	/*
	 * This method handles custom Exception That is resource not found exception.
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> resourceNotFoundException(ResourceNotFoundException exception,
			WebRequest webRequest) {

		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);

	}

	/*
	 * This method handles all hibernate validation exceptions,To write the below
	 * method, we need to extend this class with ResponseEntityExceptionHandler
	 * 
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});

		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}

	/*
	 * This method handles exceptions which haven't been covered by above 2 methods
	 */

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> generalExceptions(Exception exception, WebRequest webRequest) {

		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
				webRequest.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
