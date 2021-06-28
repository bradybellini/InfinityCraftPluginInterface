package com.playinfinitycraft.infinitycraft.utils;

public class StringHelper {

    public String everyWordToUpperCase(String s) {

        char[] stringCharArray = s.toCharArray();
        boolean foundSpace = true;

        for (int i = 0; i < stringCharArray.length; i++) {
            if (Character.isLetter(stringCharArray[i])) {
                if (foundSpace) {
                    stringCharArray[i] = Character.toUpperCase(stringCharArray[i]);
                    foundSpace = false;
                }
            } else {
                foundSpace = true;
            }
        }
        return String.valueOf(stringCharArray);
    }

    public boolean onlyLetters(String s) {

        char[] stringCharArray = s.toCharArray();

        for (int i = 0; i < stringCharArray.length; i++) {
            if (!Character.isLetter(stringCharArray[i])) {
                return false;
            }
        }
        return true;
    }

    public boolean onlyLettersAndDigits(String s) {

        char[] stringCharArray = s.toCharArray();

        for (int i = 0; i < stringCharArray.length; i++) {
            if (!Character.isLetter(stringCharArray[i]) && !Character.isDigit(stringCharArray[i])) {
                return false;
            }
        }
        return true;
    }
}
