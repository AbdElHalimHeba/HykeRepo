package ai.hyke.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class HykeApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(HykeApplication.class, args);
	}

}
