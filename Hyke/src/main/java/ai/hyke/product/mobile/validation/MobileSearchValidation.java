package ai.hyke.product.mobile.validation;

import java.util.Map;

import ai.hyke.product.mobile.entity.enums.FilterType;
import ai.hyke.product.mobile.exception.InputException;

/**
 * 
 * <h1>MobileSearchValidation Class</h1> 
 * <p>This is a utility class for mobile search operations validations.</p>
 *
 * @author Heba Abd El-Halim
 * @version 1.0 
 * @since May 2021
 *
 */
public class MobileSearchValidation {

	/**
	 * 
	 * This static method checks key names are valid Mobile fields.
	 * It throws InputException in case of invalid field.
	 * 
	 * @param filters - Map<String,String> for key & value
	 * @throws InputException - invalid key name
	 */
	public static void ValidateKey(Map<String, String> filters) {
		
		Long invalidKeys = filters.entrySet()
		.stream()
		.filter(i -> !FilterType.getKey(i.getKey()).isPresent())
		.count();
		
		if(invalidKeys > 0L)
			throw new InputException();
		
	}
	
	/**
	 * 
	 * This static method checks value formats are valid Mobile fields' type.
	 * It throws InputException in case of invalid type.
	 * 
	 * @param filters - Map<String,String> for key & value
	 * @throws InputException - invalid value format 
	 */
	public static void validateValue(Map<String, String> filters) {
		
		Long invalidValues = filters.entrySet()
		.stream()
		.filter(i -> FilterType.getValue(i.getKey(), i.getValue()) == null)
		.count();
		
		if(invalidValues > 0L)
			throw new InputException();
		
	}
}
