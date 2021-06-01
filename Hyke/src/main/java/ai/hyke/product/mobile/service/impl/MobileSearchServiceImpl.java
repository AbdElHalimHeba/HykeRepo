package ai.hyke.product.mobile.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.hyke.product.mobile.entity.Mobile;
import ai.hyke.product.mobile.exception.EmptyContentException;
import ai.hyke.product.mobile.repository.MobileSearchRepository;
import ai.hyke.product.mobile.service.MobileSearchService;
import ai.hyke.product.mobile.validation.MobileSearchValidation;

/**
 * 
 * <h1>MobileSearchServiceImpl Class</h1> 
 * <p>This is MobileSearchService implementation.</p>
 *
 * @author Heba Abd El-Halim
 * @version 1.0 
 * @since May 2021
 *
 */
@Service
public class MobileSearchServiceImpl implements MobileSearchService {

	@Autowired
	private MobileSearchRepository repository;
	
	/**
	 * 
	 * This method validates key name & throws InputException if it doesn't exist.
	 * It also validates value format & throws InputException if it's invalid.
	 * Then calls MobileSearchRepository to retrieve mobiles.
	 * It returns mobiles or throws EmptyContentException if no values satisfy input filters.
	 * 
	 * @param filters - can be null or Map<String,String> for key & value
	 * @return mobiles - list of filtered mobiles
	 * @throws InputException - invalid key name or value format 
	 * @throws EmptyContentException - no values satisfy input filters
	 */
	@Override
	public List<Mobile> search(Map<String, String> filters) {
		
		MobileSearchValidation.ValidateKey(filters);
		MobileSearchValidation.validateValue(filters);
		
		List<Mobile> mobiles = repository.filter(filters);
		
		if(mobiles.size() > 0)
			return mobiles;
		else
			throw new EmptyContentException();
		
	}
	
	/**
	 * 
	 * This method calls MobileSearchRepository to retrieve mobiles.
	 * It returns mobiles or throws EmptyContentException if no values exist.
	 * 
	 * @return mobiles - list of mobiles
	 * @throws EmptyContentException - no values exist
	 */
	@Override
	public List<Mobile> search() {
		
		List<Mobile> mobiles = repository.findAll();
		
		if(mobiles.size() > 0)
			return mobiles;
		else
			throw new EmptyContentException();
	}

}
