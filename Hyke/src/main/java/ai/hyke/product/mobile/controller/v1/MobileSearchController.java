package ai.hyke.product.mobile.controller.v1;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ai.hyke.product.mobile.entity.Mobile;
import ai.hyke.product.mobile.service.MobileSearchService;

/**
 * 
 * <h1>MobileSearchController Class</h1> 
 * <p>This controller contains mobile search operations version 1 APIs.</p>
 *
 * @author Heba Abd El-Halim
 * @version 1.0 
 * @since May 2021
 *
 */
@RestController
public class MobileSearchController {

	@Autowired
	private MobileSearchService service;
	
	/**
	 * 
	 * This API calls MobileSearchService based on input. 
	 * When input is null then retrieves all the mobiles.
	 * When having filters then retrieves the values that satisfy it.
	 *
	 * @param filters - can be null or Map<String,String> for key & value
	 * @return mobiles - list of filtered mobiles
	 * @throws InputException - invalid key name or value format 
	 * @throws EmptyContentException - no values satisfy input filters
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public List<Mobile> search(@RequestParam(required = false) Map<String, String> filters) {
		
		return filters == null || filters.isEmpty() ? service.search() : service.search(filters);

	}
}
