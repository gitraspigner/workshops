package com.pluralsight.Week5;

/**
 * *******Add program description here******
 *
 * @author Ravi Spigner
 */
public class LeaseContract extends Contract {
    private double expectedEndingValue;
    private double leaseFee;
    private final double EXPECTED_ENDING_VALUE_RATE = 0.50; //50%
    private final double LEASE_FEE_RATE = 0.07; //7%
    public LeaseContract(String date, String customerName, String customerEmail,
                         Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
        double ogVehiclePrice = vehicleSold.getPrice();
        expectedEndingValue = ogVehiclePrice * EXPECTED_ENDING_VALUE_RATE; //50% of original vehicle price
        leaseFee = ogVehiclePrice * LEASE_FEE_RATE; //7% of original vehicle price
    }
    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }
    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }
    public double getLeaseFee() {
        return leaseFee;
    }
    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }
    @Override
    public double getTotalPrice() {
        return (getVehicleSold().getPrice() * 36) + expectedEndingValue;
    }
    @Override
    public double getMonthlyPayment() {
        double principal = getVehicleSold().getPrice() - expectedEndingValue + leaseFee;
        double monthlyRate = 0.04/12.00; //convert annual finance rate 4% to monthly rate
        //monthly payment formula: M = P * (r * (1 + r)^n) / ((1 + r)^n - 1)
        //all leases are financed at 36 months at a rate of 4% (annually, not monthly)
        return principal * (monthlyRate * Math.pow(1 + monthlyRate, 36))
                / (Math.pow(1 + monthlyRate, 36) - 1);
    }
}
