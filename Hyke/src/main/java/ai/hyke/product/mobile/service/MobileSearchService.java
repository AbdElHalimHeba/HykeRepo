package ai.hyke.product.mobile.service;

import java.util.List;
import java.util.Map;

import ai.hyke.product.mobile.entity.Mobile;

/**
 * 
 * <h1>MobileSearchService Interface</h1> 
 * <p>This interface contains mobile search business operations.</p>
 *
 * @author Heba Abd El-Halim
 * @version 1.0 
 * @since May 2021
 *
 */
public interface MobileSearchService {

	List<Mobile> search(Map<String, String> filters);

	List<Mobile> search();

}
