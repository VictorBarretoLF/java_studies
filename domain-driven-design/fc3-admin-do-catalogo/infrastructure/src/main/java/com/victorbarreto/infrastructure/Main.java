package com.victorbarreto.infrastructure;

import com.victorbarreto.application.UseCase;

public class Main {
    public static void main(String[] args) {
        System.out.println(new UseCase().execute());
    }
}