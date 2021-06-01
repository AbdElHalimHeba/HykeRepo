package ai.hyke.product.mobile.entity.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 
 * <h1>FilterType Enum</h1> 
 * <p>This enum contains Mobile entity fields' names & types.</p>
 * <p>It's used for validation & query purposes.</p>
 *
 * @author Heba Abd El-Halim
 * @version 1.0 
 * @since May 2021
 *
 */
@AllArgsConstructor
@Getter
public enum FilterType {

	ID("id", Integer.class),
	BRAND("brand", String.class),
	PHONE("phone", String.class),
	PICTURE("picture", String.class),
	ANNOUNCEDATE("release.announceDate", String.class),
	PRICEEUR("release.priceEur", Double.class),
	SIM("sim", String.class),
	RESOLUTION("resolution", String.class),
	AUDIOJACK("hardware.audioJack", String.class),
	GPS("hardware.gps", String.class),
	BATTERY("hardware.battery", String.class);
	
	private String key;
	private Class<?> clazz;
	
	private static Map<String, FilterType> map;
	
	/**
	 * This map is initialized on Application start with key Enum name & value Enum.
	 */
	static {
		
		map = new HashMap<String, FilterType>();
		
		for(FilterType filter: FilterType.values()) {
			map.put(filter.name(), filter);
		}
		
	}

	/**
	 * 
	 * This method checks whether Enum name ignore case exists in map to retrieve the Mobile field name.
	 * Null otherwise.
	 * 
	 * @param name - Enum name
	 * @return Mobile field name
	 */
	public static Optional<String> getKey(String name) {
		
		return Optional.ofNullable(map.get(name.toUpperCase())).map(i -> i.getKey());
	}
	
	/**
	 * 
	 * This method checks whether Enum name ignore case exists in map to retrieve the Mobile field class Type. 
	 * Null otherwise.
	 * 
	 * @param name - Enum name
	 * @return Mobile field class type
	 */
	public static Optional<Class<?>> getClazz(String name) {

		return Optional.ofNullable(map.get(name.toUpperCase())).map(i -> i.getClazz());
	}
	
	/**
	 * 
	 * This method retrieves Mobile field class Type by input Enum name.
	 * Then checks whether the input value could be parsed to it.
	 * It returns the parsed value or null otherwise.
	 * 
	 * @param name - Enum name
	 * @param value - Filter value
	 * @return Mobile field parsed value
	 */
	public static <T extends Object> T getValue(String name, String value) {

		return getClazz(name)
		.map(i -> {			
			try {
				
				if (i.isAssignableFrom(Integer.class))
					return (T) Integer.valueOf(value);
				else if (i.isAssignableFrom(Double.class))
					return (T) Double.valueOf(value);
		
				return (T) value;
			
			} catch(IllegalArgumentException e) {
				return null;
			}
		}).orElse(null);
		
	}

}
