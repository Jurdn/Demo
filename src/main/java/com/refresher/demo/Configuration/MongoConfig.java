package com.refresher.demo.Configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collection;
import java.util.Collections;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {
    @Override
    protected String getDatabaseName() {
        return "demo";
    }

    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString("mongodb+srv://root:root@demo.jacenny.mongodb.net/?retryWrites=true&w=majority");
        MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).build();
        return MongoClients.create(clientSettings);
    }

    @Override
    public Collection getMappingBasePackages() {
        return Collections.singleton("com.refresher");
    }
}
