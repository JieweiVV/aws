package com.example.myapp;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import com.amazonaws.secretsmanager.caching.SecretCache;

public class SecretManagerTest {
    public static void main( String[] args ) {
        SecretCache cache  = new SecretCache();
        ByteBuffer buffer = cache.getSecretBinary("customer/santos_pasa_pumpkin/stripe_outbound_api_key/base64");
        String s = StandardCharsets.UTF_8.decode(buffer).toString();
        System.out.println(s);
    }
}
