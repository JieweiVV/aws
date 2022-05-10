package com.example.myapp.DDBTest;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
@DynamoDBTable(tableName = "ErrorTokenInfo")
public class ErrorTokenInfo {

    @NonNull
    @DynamoDBHashKey(attributeName = "pszToken")
    private String pszToken;

    @NonNull
    @DynamoDBTypeConverted(converter = PszTokenDynamoDbTypeConverter.class)
    private PszTokenType pszTokenType;

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName="errorSource")
    private ErrorSource errorSource;

    @DynamoDBAttribute(attributeName="isactive")
    private boolean isActive;
}

