package com.studies.urlshortener.utils;

public interface BaseEncoder {
    String encode(long number);

    long decode(String number);
}
