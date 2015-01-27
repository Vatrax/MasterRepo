package pl.edu.wroc.pwr.service;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import java.util.Arrays;
import java.util.List;

/**
 * Created by krzaczek on 01.11.14.
 */
@Configuration
public class SpringMongoConfig extends AbstractMongoConfiguration {

	@Override
	protected String getDatabaseName() {
		return EnvironmentVariables.APP_NAME;
	}

	@Override
	@Bean
	public Mongo mongo() throws Exception {
		MongoCredential credential = MongoCredential.createMongoCRCredential(EnvironmentVariables.DB_USERNAME,
			getDatabaseName(), EnvironmentVariables.DB_PASSWORD.toCharArray());
		List<MongoCredential> credentialsList = Arrays.asList(credential);
		ServerAddress serverAddress = new ServerAddress(EnvironmentVariables.DB_HOST, EnvironmentVariables.DB_PORT);
		return new MongoClient(serverAddress, credentialsList);
	}

}

