package com.team.twodari.common;

import com.team.twodari.common.constant.PasswordGeneratorConstant;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PasswordGenerator {
    private static final SecureRandom random = new SecureRandom();

    public static String generateRandomPassword(int length) {
        List<String> passwordChars = new ArrayList<>();
        passwordChars.add(randomChar(PasswordGeneratorConstant.ENG_UPPER_CASE));
        passwordChars.add(randomChar(PasswordGeneratorConstant.ENG_LOWER_CASE));
        passwordChars.add(randomChar(PasswordGeneratorConstant.DIGITS));
        passwordChars.add(randomChar(PasswordGeneratorConstant.SPECIAL_CHARS));
        Stream<String> otherChars =
                Stream.generate(() ->
                        randomChar(PasswordGeneratorConstant.ENG_UPPER_CASE + PasswordGeneratorConstant.ENG_LOWER_CASE + PasswordGeneratorConstant.DIGITS + PasswordGeneratorConstant.SPECIAL_CHARS)).limit(length - 4);
        passwordChars.addAll(otherChars.collect(Collectors.toList()));
        Collections.shuffle(passwordChars);
        String newPassword = passwordChars.stream().collect(Collectors.joining());
        return newPassword;
    }

    private static String randomChar(String characters) {
        return String.valueOf(characters.charAt(random.nextInt(characters.length())));
    }
}
