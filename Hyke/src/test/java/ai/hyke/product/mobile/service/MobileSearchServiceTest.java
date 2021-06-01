package ai.hyke.product.mobile.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ai.hyke.product.mobile.entity.Mobile;
import ai.hyke.product.mobile.exception.EmptyContentException;
import ai.hyke.product.mobile.exception.InputException;
import ai.hyke.product.mobile.repository.MobileSearchRepository;

/**
 * 
 * <h1>MobileSearchServiceTest Class</h1> 
 * <p>This class tests mobile search business operations.</p>
 *
 * @author Heba Abd El-Halim
 * @version 1.0 
 * @since May 2021
 *
 */
@SpringBootTest
public class MobileSearchServiceTest {

	@Autowired
	private MobileSearchService service;
	
	@MockBean
	private MobileSearchRepository repository;
	
	/**
	 * This TC asserts valid filter retrieves 200 & the expected value.
	 */
	@Test
	public void whenSearchFiltersValid_thenStatus200() {
	
		List<Mobile> mobiles = new ArrayList<Mobile>() 
		{{ add(new Mobile("_id", 1, "brand", "phone", "picture", null, "sim", "resolution", null)); }};
		
		Map<String, String> filters = getFilters("id", "1");
		
		when(repository.filter(filters)).thenReturn(mobiles);
		
		assertNotNull(service.search(filters));
	}
	
	/**
	 * This TC asserts 204 is retrieved when filter doesn't have a corresponding value.
	 */
	@Test
	public void whenSearchFiltersNotExist_thenStatus204() {
	
		Map<String, String> filters = getFilters("id", "1");
		
		when(repository.filter(filters)).thenReturn(new ArrayList<Mobile>());
		
		assertThrows(EmptyContentException.class, () -> service.search(filters))
		.getClass().equals(EmptyContentException.class);
		
	}
	
	/**
	 * This TC asserts 400 is retrieved when filter key name doesn't exist.
	 */
	@Test
	public void whenSearchFiltersInValidKeyName_thenStatus400() {
	
		Map<String, String> filters = getFilters("idKey", "1");
		
		assertThrows(InputException.class, () -> service.search(filters))
		.getClass().equals(InputException.class);

	}
	
	/**
	 * This TC asserts 400 is retrieved when filter value format is invalid.
	 */
	@Test
	public void whenSearchFiltersInValidValueFormat_thenStatus400() {
	
		Map<String, String> filters = getFilters("id", "1x");
		
		assertThrows(InputException.class, () -> service.search(filters))
		.getClass().equals(InputException.class);
		
	}
	
	/**
	 * This TC asserts 503 is retrieved in accidental issues.
	 */
	@Test
	public void whenGeneralError_thenStatus503() {
	
		assertThrows(Exception.class, () -> service.search(null))
		.getClass().equals(Exception.class);
		
	}
	
	private Map<String, String> getFilters(String key, String value) {
		
		return new HashMap<String, String>() {{ put(key, value); }};
		
	}
	
}
