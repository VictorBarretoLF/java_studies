package com.studies.urlshortener.utils;

public enum BaseEncodingType {
    BASE_2("01"),
    BASE_16("0123456789ABCDEF"),
    BASE_58("123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz"),
    BASE_62("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");

    private final String characters;

    BaseEncodingType(String characters) {
        this.characters = characters;
    }

    public String getCharacters() {
        return characters;
    }

    public int getBase() {
        return characters.length();
    }
}
