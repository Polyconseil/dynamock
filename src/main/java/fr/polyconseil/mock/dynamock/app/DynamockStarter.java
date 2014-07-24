package fr.polyconseil.mock.dynamock.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages="fr.polyconseil.mock")
public class DynamockStarter {

	public static void main(String[] args) throws Exception {
	    SpringApplication app = new SpringApplication(DynamockStarter.class);
        app.setShowBanner(false);
        app.run(args);
	}

//	@Bean public MongoFactoryBean mongo() {
//		MongoFactoryBean mongo = new MongoFactoryBean();
//		mongo.setHost("localhost");
//		mongo.setMongoOptions(mongoOptions);
//		return mongo;
//	}
	// public @Bean MongoDbFactory mongoDbFactory() throws Exception {
	// UserCredentials userCredentials = new UserCredentials("joe", "secret");
	// return new SimpleMongoDbFactory(new Mongo(), "database", userCredentials);
	// }
	//
	// public @Bean MongoTemplate mongoTemplate() throws Exception {
	// return new MongoTemplate(mongoDbFactory());
}
