package ai.hyke.product.mobile.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * <h1>Mobile Entity</h1> 
 * <p>Mapping of mobile Collection in product DB.</p>
 *
 * @author Heba Abd El-Halim
 * @version 1.0 
 * @since May 2021
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Document
public class Mobile {

	@MongoId
	private String _id; 
	private Integer id;
	private String brand;
	private String phone;
	private String picture;
	private Release release;
	private String sim;
	private String resolution;
	private Hardware hardware;
	
}
