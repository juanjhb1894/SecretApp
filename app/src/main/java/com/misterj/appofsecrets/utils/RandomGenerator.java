package com.misterj.appofsecrets.utils;

import java.util.Random;
import java.util.UUID;

public class RandomGenerator {

    public String stringUsingRandom(int length)
    {
        Random random = new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        StringBuilder sb = new StringBuilder(length);
        for(int i = 0; i <length; i++)
        {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public String stringUsingUUID(int length)
    {
        String randomUUID = UUID.randomUUID().toString().replace("-","");
        int maxLength = Math.min(length, randomUUID.length());
        return randomUUID.substring(0, maxLength);
    }
}
