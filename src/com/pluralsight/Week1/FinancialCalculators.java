package com.pluralsight.Week1;

/**
 * Calculates and displays results for 3 different financial annuity calculators: mortgage,
 * present value, and future value.
 *
 * @author Ravi Spigner
 */
public class FinancialCalculators {
    public static void main(String[] args) {
        //Notes:
        //-for getting input values, just use method calls (only get Scanner input
        // if time allows, it's more important to have formulas correctly working)
        //-not all formulas need to be completed
        //-also, * and × both mean multiplication

        //calc1 mortgage test
        int principalLoanAmount = 53000;
        double annualInterestRate = 7.625;
        int loanYears = 15;
        double monthlyPayment = calc1MortgageMonthlyPayment(principalLoanAmount, annualInterestRate,loanYears);
        double totalInterest = calc1MortgageTotalInterest(monthlyPayment, loanYears, principalLoanAmount);
        System.out.printf("A $%,d loan at %.3f%% interest for %d years would have a $%.2f/mo " +
                "payment with a total interest of $%,.2f.%n%n",principalLoanAmount,
                annualInterestRate,loanYears, monthlyPayment, totalInterest);

        //calc 2 future value test
        int principalDeposit = 1000;
        double interestRate = 1.75;
        int yearsElapsed = 5;
        double futureValue = calc2FutureValue(principalDeposit, interestRate, yearsElapsed);
        double futureValueTotalInterest = calc2FutureValueTotalInterest(futureValue,principalDeposit);
        System.out.printf("If you deposit $%,d in your CD that earns %.2f%% interest " +
                        "and matures in %d years, your CD's%nending balance will be $%.2f " +
                        "and you would have earned $%,.2f in interest.%n%n",principalDeposit,
                interestRate,yearsElapsed, futureValue, futureValueTotalInterest);

        //calc 3 present value test
        int monthlyPayout = 3000;
        double expectedInterestRate = 2.5;
        int years = 20;
        double presentValue = calc3PresentValue(monthlyPayout, expectedInterestRate, years);
        System.out.printf("To fund an annuity that pays $%,d monthly for %d years and earns " +
                "an expected %.1f%% interest, you would need to invest $%,.2f today.%n%n",
                monthlyPayout, years, expectedInterestRate, presentValue);
    }

    /**
     * calculator 1 formula - calculates monthly payment amount for a loan based off of
     * principal loan amount, annual interest rate, and loan years
     * M=P×(i*(1+i)^n / ((1+i)^n)-1)
     *
     * M = monthly payment amount (what we're calculating)
     * P = principalLoanAmount
     * i = annualInterestRate/12 (to give monthly interest rate)
     * n = loanYears*12 (to give number of monthly payments)
     */
    public static double calc1MortgageMonthlyPayment(int principalLoanAmount,
                                                     double annualInterestRate, int loanYears) {
        //convert annualInterestRate (ex. 7.625) to decimal form (/100.00),
        // then to monthly interest rate (/12.00)
        double monthlyInterestRate = ((annualInterestRate/100.00)/12.00);
        //convert loan years (ex. 15 years) to months (*12)
        int loanMonths = loanYears*12;
        return principalLoanAmount*(
                (monthlyInterestRate*Math.pow(1+monthlyInterestRate,loanMonths)) /
                (Math.pow(1+monthlyInterestRate,loanMonths)-1));
    }
    /**
     * calculator 1 formula - calculates total interest for a loan based off of
     * monthly payment amount, loan years, and principal loan amount
     * T = (M×n)−P
     *
     * T = total interest (what we're calculating)
     * M = monthly payment (value returned by calc1MortgageMonthlyPayment)
     * n = loanYears*12 (to give number of monthly payments)
     * P = principalLoanAmount
     */
    public static double calc1MortgageTotalInterest(double monthlyPayment, int loanYears,
                                                    int principalLoanAmount) {
        //convert loan years (ex. 15 years) to months (*12)
        int loanMonths = loanYears*12;
        return (monthlyPayment*loanMonths)-principalLoanAmount;
    }

    /**
     * calculator 2 formula - calculates future value for a one-time deposit assuming compound
     * interest based off of deposit amount, interest rate, and years elapsed
     * FV=P×((1+(r/n))^n⋅t)
     *
     * FV = future value (what we're calculating)
     * P = principal deposit (initial amount)
     * r = annual interest rate (in decimal, e.g. 0.05 for 5%)
     * n = compounding frequency (times per year) daily compounding assumed (e.g. 365 days)
     * t = number of years
     */
    public static double calc2FutureValue(int deposit, double annualInterestRate,
                                                    int years) {
        //convert annualInterestRate (ex. 1.75) to decimal form (/100.00),
        double interestRate = (annualInterestRate/100.00);

        return deposit*(
                Math.pow(1+(interestRate/365.00), (365.00*years)));
    }
    /**
     * calculator 2 formula - calculates total interest of future value
     * monthly payment amount, loan years, and principal loan amount
     * T = FV - P
     *
     * T = future value total interest (what we're calculating)
     * FV = future value (value returned by calc2FutureValue)
     * P = principal deposit (initial amount)
     */
    public static double calc2FutureValueTotalInterest(double futureValue, int principalDeposit) {
        return futureValue - principalDeposit;
    }

    /**
     * calculator 3 formula - calculates present value of an ordinary annuity given the
     * monthly payout, expected interest rate, and years to pay out.
     * PV = PMT×((1-(1/((1+i)^n)))/i)
     *
     * PV = present value (the amount today) (what we're calculating)
     * PMT = the fixed payment per period (your monthlyPayout)
     * i = interest rate per period, which is yearly (your expectedInterestRate)
     * N = total number of monthly payments (given by payoutYears*12)
     */
    public static double calc3PresentValue(int monthlyPayout, double expectedInterestRate,
                                           int payoutYears) {
        //convert expectedInterestRate (ex. 2.5, which is yearly) to decimal form (/100.00),
        //and then to monthly (/12.00)
        double monthlyInterestRate = (expectedInterestRate/100.00)/12.00;
        //convert payout years (ex. 20 years) to months (*12)
        int months = payoutYears*12;

        return monthlyPayout*(
                (1-(1/(Math.pow(1+monthlyInterestRate, months)))))/monthlyInterestRate;
    }
}