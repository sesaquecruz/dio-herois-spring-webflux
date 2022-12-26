package com.heroes.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DynamoDBTables implements CommandLineRunner {
    public static final String TABLE_NAME = "HEROES";
    @Autowired
    private DynamoDBConfig dynamoDBConfig;

    @Override
    public void run(String... args) throws Exception {
        EndpointConfiguration endpointConfiguration = dynamoDBConfig.endpointConfiguration();
        AWSCredentials awsCredentials = dynamoDBConfig.awsCredentials();
        AmazonDynamoDB amazonDynamoDB = dynamoDBConfig.amazonDynamoDB(endpointConfiguration, awsCredentials);

        try {
            CreateTableResult table = amazonDynamoDB.createTable(
                    List.of(new AttributeDefinition("name", ScalarAttributeType.S)),
                    TABLE_NAME,
                    List.of(new KeySchemaElement("name", KeyType.HASH)),
                    new ProvisionedThroughput(5L, 5L)
            );
        } catch (ResourceInUseException ignored) {}

        System.out.println("DynamoDB: The table " + TABLE_NAME + " is created.");
    }
}
