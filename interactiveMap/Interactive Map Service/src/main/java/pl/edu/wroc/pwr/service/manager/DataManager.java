package pl.edu.wroc.pwr.service.manager;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import pl.edu.wroc.pwr.service.SpringMongoConfig;

/**
 * Created by krzaczek on 01.11.14.
 */
public class DataManager {

	private final ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
	protected final MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

}
