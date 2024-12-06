package com.studies.urlshortener.utils;

public class Base10Encoder implements BaseEncoder {

    private final BaseEncodingType type;
    private final String characters;
    private final int base;

    public Base10Encoder(BaseEncodingType type) {
        this.type = type;
        this.characters = type.getCharacters();
        this.base = type.getBase();
    }

    @Override
    public String encode(long number) {
        StringBuilder stringBuilder = new StringBuilder(1);
        do {
            stringBuilder.insert(0, characters.charAt((int) (number % base)));
            number /= base;
        } while (number > 0);
        return stringBuilder.toString();
    }

    @Override
    public long decode(String number) {
        long result = 0L;
        int length = number.length();
        for (int i = 0; i < length; i++) {
            result += (long) Math.pow(base, i) * characters.indexOf(number.charAt(length - i - 1));
        }
        return result;
    }

    // Static convenience methods that don't require you to instantiate the class
    public static String encode(long number, BaseEncodingType type) {
        return new Base10Encoder(type).encode(number);
    }

    public static long decode(String encoded, BaseEncodingType type) {
        return new Base10Encoder(type).decode(encoded);
    }

}
