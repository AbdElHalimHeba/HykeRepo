package ai.hyke.product.mobile.repository;

import java.util.List;
import java.util.Map;

import ai.hyke.product.mobile.entity.Mobile;

/**
 * 
 * <h1>CustomMobileSearchRepository Interface</h1> 
 * <p>This interface contains mobile search custom DAL operations.</p>
 *
 * @author Heba Abd El-Halim
 * @version 1.0 
 * @since May 2021
 *
 */
public interface CustomMobileSearchRepository {

	List<Mobile> filter(Map<String, String> filters);
}
