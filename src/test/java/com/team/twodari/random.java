package com.team.twodari;

import com.team.twodari.common.constant.PasswordGeneratorConstant;
import java.security.SecureRandom;

public class random {
   Integer count= generatePasswordLength();
    private static final SecureRandom random = new SecureRandom();
    public static  Integer generatePasswordLength(){
        Integer randomLength = random.nextInt(PasswordGeneratorConstant.MAX_PASSWORD_LENGTH - PasswordGeneratorConstant.MIN_PASSWORD_LENGTH + 1) + PasswordGeneratorConstant.MIN_PASSWORD_LENGTH;
        System.out.println(randomLength);
        return randomLength;
    }

    public void test(){
        System.out.println(count);
    }

}
