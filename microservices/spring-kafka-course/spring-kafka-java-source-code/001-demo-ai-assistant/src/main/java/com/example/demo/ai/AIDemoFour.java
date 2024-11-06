package com.example.demo.ai;

public class AIDemoFour {
    public static void main(String[] args) {
        // Loop through numbers 1 to 5 for the multiplication table
        for (int i = 1; i <= 5; i++) {
            // Nested loop to multiply 'i' with numbers 1 to 5
            for (int j = 1; j <= 5; j++) {
                System.out.println("The multiplication result of " + i + " and " + j + " is " + (i * j));
            }
            // Print a blank line after each multiplication table for readability
            System.out.println();
        }
    }
}