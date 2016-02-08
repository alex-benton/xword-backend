package xword;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collections;

/**
 * Set up our database based on the configuration set in the application configs.
 *
 * Spring magic happens here.
 *
 * @author alex
 */
@Configuration
@EnableMongoRepositories
public class MongoConfiguration extends AbstractMongoConfiguration {

    private static final String DB_NAME = System.getenv("DB_NAME");
    private static final String DB_HOST = System.getenv("DB_HOST");
    private static final int DB_PORT = Integer.parseInt(System.getenv("DB_PORT"));
    private static final String DB_USER = System.getenv("DB_USER");
    private static final String DB_PASS = System.getenv("DB_PASS");

    @Override
    protected String getDatabaseName() {
        return DB_NAME;
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(
                new ServerAddress(DB_HOST, DB_PORT),
                Collections.singletonList(
                        MongoCredential.createCredential(
                                DB_USER,
                                DB_NAME,
                                DB_PASS.toCharArray())));
    }
}
