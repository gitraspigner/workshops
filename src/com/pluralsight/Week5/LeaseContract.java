package com.pluralsight.Week5;
/**
 * A lease contract is a type of contract which represents a lease for a vehicle.
 * A lease contract contains info from a base-level contract including
 * customer info, the vehicle sold, the date of purchase, and the total price of the sale.
 * The total price of the lease is dependent on several factors: expected ending value (50%
 * of the original vehicle price), monthly payment amount (all leases are financed at 4.0% for
 * 36 months).
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
    public String toString() {
        Vehicle vehicleSold = super.getVehicleSold();
        return "LEASE, Date Leased: " + super.getDate() + ", Customer Name: " +
                super.getCustomerName() + ", Customer Email: " + super.getCustomerEmail() +
                ", VIN Number: " + vehicleSold.getVin() + ", Vehicle Year: " +
                vehicleSold.getYear() + ", Make: " +
                vehicleSold.getMake() + ", Model: " +
                vehicleSold.getModel() + ", Type: " +
                vehicleSold.getVehicleType() + ", Color: " +
                vehicleSold.getColor() + ", Odometer: " +
                vehicleSold.getOdometer() + ", Price: " +
                String.format("%.2f", vehicleSold.getPrice()) + ", Expected Ending Value: " +
                String.format("%.2f", expectedEndingValue) + ", Lease Fee: " +
                String.format("%.2f", leaseFee) + ", Total Lease Price: " +
                String.format("%.2f", getTotalPrice()) + ", Monthly Payment: " +
                String.format("%.2f", getMonthlyPayment());
    }
    @Override
    public String toStringForFileWrite() {
        Vehicle vehicleSold = super.getVehicleSold();
        return "LEASE|" + super.getDate() + "|" + super.getCustomerName() +
                "|" + super.getCustomerEmail() + "|" + vehicleSold.getVin() +
                "|" + vehicleSold.getYear() + "|" +
                vehicleSold.getMake() + "|" +
                vehicleSold.getModel() + "|" +
                vehicleSold.getVehicleType() + "|" +
                vehicleSold.getColor() + "|" +
                vehicleSold.getOdometer() + "|" +
                vehicleSold.getPrice() + "|" +
                expectedEndingValue + "|" +
                leaseFee + "|" +
                getTotalPrice() + "|" +
                getMonthlyPayment();
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
