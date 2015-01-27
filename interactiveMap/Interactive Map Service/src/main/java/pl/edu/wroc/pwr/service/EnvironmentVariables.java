package pl.edu.wroc.pwr.service;

/**
 * Created by krzaczek on 18.11.14.
 */
public interface EnvironmentVariables {

	String DB_HOST = System.getenv("OPENSHIFT_MONGODB_DB_HOST");
	String DB_USERNAME = System.getenv("OPENSHIFT_MONGODB_DB_USERNAME");
	String DB_PASSWORD = System.getenv("OPENSHIFT_MONGODB_DB_PASSWORD");
	String APP_NAME = System.getenv("OPENSHIFT_APP_NAME");
	Integer DB_PORT = Integer.valueOf(System.getenv("OPENSHIFT_MONGODB_DB_PORT"));
	String DEPLOYMENTS_PATH = System.getenv("OPENSHIFT_JBOSSAS_DIR");

}
