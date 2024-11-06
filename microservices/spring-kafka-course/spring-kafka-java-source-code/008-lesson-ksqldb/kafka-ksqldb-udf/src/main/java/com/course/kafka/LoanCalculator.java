package com.course.kafka;

public class LoanCalculator {

    public static double round(double original, int decimalPlaces) {
        double factor = Math.pow(10, decimalPlaces);
        return Math.round(original * factor) / factor;
    }

    public static double calculateMonthlyInstallment(
            double principalLoanAmount, double annualInterestRate, int loanPeriodMonth) {
        if (principalLoanAmount < 1 || annualInterestRate < 1 || loanPeriodMonth < 1) {
            throw new IllegalArgumentException("Principal, interest rate, and loan period must be greater than 0");
        }

        var interest = (annualInterestRate / 100d) * (loanPeriodMonth / 12d) * principalLoanAmount;
        var totalPayment = principalLoanAmount + interest;

        return round(totalPayment / loanPeriodMonth, 2);
    }

}
