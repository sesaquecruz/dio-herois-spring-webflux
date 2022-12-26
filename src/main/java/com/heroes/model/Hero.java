package com.heroes.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;

import com.heroes.config.DynamoDBTables;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DynamoDBTable(tableName = DynamoDBTables.TABLE_NAME)
public class Hero {
    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private String name;
    @DynamoDBAttribute
    private String power;
    @DynamoDBAttribute
    private String films;
}
