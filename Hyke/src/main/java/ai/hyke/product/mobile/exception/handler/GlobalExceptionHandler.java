package ai.hyke.product.mobile.exception.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ai.hyke.product.mobile.exception.EmptyContentException;
import ai.hyke.product.mobile.exception.InputException;

/**
 * 
 * <h1>GlobalExceptionHandler Class</h1> 
 * <p>This class maps thrown exceptions.</p>
 *
 * @author Heba Abd El-Halim
 * @version 1.0 
 * @since May 2021
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 
	 * @param InputException
	 * @return 400
	 */
	@ExceptionHandler(InputException.class)
	public ResponseEntity<String> handleInputException(InputException exception) {

		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * 
	 * @param EmptyContentException
	 * @return 204
	 */
	@ExceptionHandler(EmptyContentException.class)
	public ResponseEntity<String> handleEmptyContentException(EmptyContentException exception) {

		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * 
	 * @param Exception
	 * @return 503
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGeneralException(Exception exception) {

		return new ResponseEntity<String>(HttpStatus.SERVICE_UNAVAILABLE);
	}
}
