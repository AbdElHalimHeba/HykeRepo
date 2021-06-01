package ai.hyke.product.mobile.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ai.hyke.product.mobile.entity.Mobile;

/**
 * 
 * <h1>MobileSearchRepository Interface</h1> 
 * <p>This interface contains mobile search DAL operations.</p>
 * <p>It uses MongoRepository for Spring Mongo implemented CRUD operations.</p>
 * <p>It uses CustomMobileSearchRepository for custom CRUD operations.</p>
 * <p>Spring Application Context will look for implementation same name + "Impl"</p>
 *
 * @author Heba Abd El-Halim
 * @version 1.0 
 * @since May 2021
 *
 */
public interface MobileSearchRepository extends MongoRepository<Mobile, String>, CustomMobileSearchRepository {

}
