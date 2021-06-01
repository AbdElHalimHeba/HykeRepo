package ai.hyke.product.mobile.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import ai.hyke.product.mobile.entity.Mobile;
import ai.hyke.product.mobile.entity.enums.FilterType;
import ai.hyke.product.mobile.repository.CustomMobileSearchRepository;

/**
 * 
 * <h1>CustomMobileSearchRepositoryImpl Class</h1> 
 * <p>This is CustomMobileSearchRepository implementation.</p>
 *
 * @author Heba Abd El-Halim
 * @version 1.0 
 * @since May 2021
 *
 */
@Repository
public class CustomMobileSearchRepositoryImpl implements CustomMobileSearchRepository {

	@Autowired
    private MongoOperations mongoOperations;
	
	/**
	 * 
	 * This method retrieves mobiles from DB or empty list if no values satisfy Criteria.
	 * 
	 * @param filters - Map<String,String> for key & value
	 * @return mobiles - list of filtered mobiles
	 */
	@Override
	public List<Mobile> filter(Map<String, String> filters) {
		
		Criteria criteria = filters.entrySet()
		.stream()
		.map(i -> buildCriteria(i.getKey(), i.getValue()))
		.reduce((i,j) -> i.andOperator(j))
		.get();
	
		MatchOperation match = Aggregation.match(criteria);
		TypedAggregation<Mobile> aggregate = Aggregation.newAggregation(Mobile.class, match);

		return mongoOperations.aggregate(aggregate, Mobile.class).getMappedResults();

	}
	
	/**
	 * 
	 * This private method builds Criteria for each filter. 
	 * It checks whether the input key type is String to build Criteria ignoring value case.
	 * Or build Criteria that matches the input value otherwise.
	 * 
	 * @param key - Mobile field key 
	 * @param value - Mobile field value
	 * @return Criteria - Mongo Criteria
	 */
	private Criteria buildCriteria(String key, String value) {
		
		return FilterType.getClazz(key).get().isAssignableFrom(String.class)
				? Criteria.where(FilterType.getKey(key).get()).regex(FilterType.getValue(key, value), "i")
				: Criteria.where(FilterType.getKey(key).get()).is(FilterType.getValue(key, value));
	}

	
}
