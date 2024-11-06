package com.example.demo.ai;

public class AIDemoTwo {

    // show numbers divisible by 3 between 1 and 100
    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            if (i % 3 == 0) {
                System.out.println(i);
            }
        }
    }

}
