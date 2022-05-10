package com.jiewei.aws;

import java.io.IOException;
import java.util.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        String value = "BJ67mDFzBuFGSWwo51YaH/aR5k8z46A43CopRV6GVV6yfFWo3b5mtSLYNOOKv/+GlXSQZo9Phwbaok0jdyqnf5FMcOtNG6ZbgB5PCdpREaWQP9u7Nq/XCBm+2XU0TbHjzpPmYbg8Y5EJ95iTwhvlfbp1XjVAMNjuhO/kbpF/OqxIO6UU7RVh7e3U+8c1UGmWSvN3uDQMxktxlGak485LpTdss2A5DySAdIC2PpwMk+LYUkzklQeUedwyaRNtgVjDP3W2X3yyL+qaaizbuu46A5mKLhwLerC2zlKxngKHB/eCgV5kZiBBtY174IQySr2iYnRSTitnC/vvXbqZ+Z3K/tJk7OQdno83namubtSu1FQF3w4YnVJ2JUuRjaM6H0RT2d2/nJ2VaKIY";
        byte[] cipherText = Base64.getDecoder().decode(value); 
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\"amount\":133333.898774}";

        String result = generateSqlQueryForRecentlyRegisteredRewardsInstruments("ptech", "startDate", 100, "NA", "wjwbucket", "iamrole");
        System.out.println(result);
        
    }

    public static String generateSqlQueryForRecentlyRegisteredRewardsInstruments(
            String issuer,
            String startDate,
            int goBackDays,
            String region,
            String s3Bucket,
            String redshiftIamRoleArn) {
        return String.format(
                "UNLOAD ($$%n" +
                "SELECT *%n" +
                "FROM (" +
                "WITH t1 as (SELECT payment_instrument_id FROM charybdis_ddl.wallet_payment_instruments WHERE issuer = '%s'),%n" +
                "t2 as (SELECT related_payment_instrument_id FROM charybdis_ddl.wallet_payment_instruments " +
                "WHERE payment_instrument_type = 'R' AND wallet_region_id = '%s' AND related_payment_instrument_id IN (SELECT * FROM t1) " +
                "AND creation_date > DATEADD('day', %s, '%s') AND creation_date < DATEADD('day', -2, '%s')),%n" +
                "t3 as (SELECT paymentinstrumentid, customerid FROM token_info WHERE lumostoken <> ''%n" +
                "AND lumostoken IS NOT NULL)%n" +
                "SELECT  customer_id, %n" +
                "payment_instrument_id,%n" +
                "wallet_region_id, %n" +
                "1,%n" +
                "token, %n" +
                "'%s', %n" +
                "last_updated_date %n" +
                "FROM%n" +
                "charybdis_ddl.wallet_payment_instruments %n" +
                "WHERE%n" +
                "payment_instrument_id IN (SELECT * FROM t2) %n" +
                "AND (payment_instrument_id, customer_id) NOT IN (SELECT paymentinstrumentid, customerid%n " +
                "FROM t3 WHERE t3.paymentinstrumentid = payment_instrument_id AND t3.customerid = customer_id)%n" +
                "AND token <> '' %n" +
                "AND token is not null)$$)%n" +
                "to 's3://%s/raw/AutoExport/%s/%s/%s_issued_rewards'%n" +
                "iam_role '%s'%n" +
                "parallel off%n" +
                "maxfilesize 5 mb;",
                issuer,
                region,
                String.valueOf(-1 * goBackDays - 2),
                startDate,
                startDate,
                issuer,
                s3Bucket,
                startDate,
                issuer,
                issuer,
                redshiftIamRoleArn
        );
    }
}
