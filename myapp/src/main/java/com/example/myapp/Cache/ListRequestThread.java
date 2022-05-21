package com.example.myapp.Cache;

import java.util.Random;

import com.google.common.cache.LoadingCache;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ListRequestThread implements Runnable{

    private final LoadingCache<String, Boolean> cache;

    @Override
    public void run() {
        Random rand = new Random();
        int rand_int1 = rand.nextInt(1000);
        if (rand_int1 %2 == 0) {
            
            cache.getUnchecked(rand_int1);
        }
        
    }
    
}
