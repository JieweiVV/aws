package com.example.myapp.DDBTest;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

public class PszTokenDynamoDbTypeConverter implements DynamoDBTypeConverter<String, PszTokenType> {

    @Override
    public String convert(PszTokenType pszTokenType) {
        return pszTokenType.name();
    }

    @Override
    public PszTokenType unconvert(String name) {
        return PszTokenType.valueOf(name);
    }
}