package com.example.myapp.DDBTest;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class DDBTest {
    public void printname() {
        System.out.println(this.getClass().getSimpleName());
    }
    public static void main( String[] args ) {
        DynamoDBMapper mapper = new DynamoDBMapper(AmazonDynamoDBClientBuilder.standard().build());
        // ErrorTokenInfo errorTokenInfo = ErrorTokenInfo.builder().pszToken("testToken12").pszTokenType(PszTokenType.Card)
        // .errorSource(ErrorSource.Dynamodb).isActive(true).build();
        // mapper.save(errorTokenInfo);
        
        ErrorTokenInfo result = mapper.load(ErrorTokenInfo.class, "ahh");
        if (result == null) {
            System.out.println("hhh");
        }
        // System.out.println(result.getPszToken());
        // System.out.println(result.getPszTokenType().name());
        // System.out.println(result.getErrorSource());
        // System.out.println(result.isActive());
    }
    

}
