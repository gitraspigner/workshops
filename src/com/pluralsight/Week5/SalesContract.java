package com.pluralsight.Week5;
/**
 * A sales contract is a type of contract which represents a sale for a vehicle. Vehicle sales
 * can be financed. A sales contract contains info from a base-level contract including
 * customer info, the vehicle sold, the date of purchase, and the total price of the sale.
 * The total price of the sale is dependent on several factors: processing fee ($295 for
 * vehicles under $10,000 and $495 for all others), (if financed) monthly payment amount (the
 * loan rate is 4.25% for vehicles priced at over $10,000 for 48 months, and the loan rate is
 * 5.25% for all others).
 *
 * @author Ravi Spigner
 */
public class SalesContract extends Contract {
    private final double SALES_TAX_AMOUNT = 00.05;
    private final double RECORDING_FEE = 100.00;
    private double processingFee;
    private boolean isFinanced;
    //no field for monthly payment since it will be retrieved from getMonthlyPayment()
    public SalesContract(String date, String customerName, String customerEmail,
                    Vehicle vehicleSold, boolean isFinanced) {
        super(date, customerName, customerEmail, vehicleSold);
        this.isFinanced = isFinanced;
        if(vehicleSold.getPrice() < 10_000.00) {
            this.processingFee = 295.00;
        } else {
            this.processingFee = 495.00;
        }
    }
    public double getProcessingFee() {
        return processingFee;
    }
    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }
    public boolean isFinanced() {
        return isFinanced;
    }
    public void setFinanced(boolean financed) {
        isFinanced = financed;
    }
    @Override
    public double getTotalPrice() {
        return super.getVehicleSold().getPrice() +
                (super.getVehicleSold().getPrice()*SALES_TAX_AMOUNT) +
                RECORDING_FEE + processingFee;
    }
    @Override
    public double getMonthlyPayment() {
        if (!isFinanced) {
            return 0.0;
        } else {
            double financeRate; //annual finance rate, will need to be converted to monthly rate
            double months;
            if (super.getVehicleSold().getPrice() < 10_000.00) {
                financeRate = 0.0425; //annually 4.25%
                months = 48.00;
            } else {
                financeRate = 0.0545; //annually 5.45%
                months = 24.00;
            }
            double principal = getTotalPrice();
            double monthlyRate = financeRate/12.00; //convert annual finance rate to monthly rate
            //monthly payment formula: M = P * (r * (1 + r)^n) / ((1 + r)^n - 1)
            return principal * (monthlyRate * Math.pow(1 + monthlyRate, months))
                    / (Math.pow(1 + monthlyRate, months) - 1);
        }
    }
    @Override
    public String toString() {
        Vehicle vehicleSold = super.getVehicleSold();
        return "SALE, Date Sold: " + super.getDate() + ", Customer Name: " +
                super.getCustomerName() + ", Customer Email: " + super.getCustomerEmail() +
                ", VIN Number: " + vehicleSold.getVin() + ", Vehicle Year: " +
                vehicleSold.getYear() + ", Make: " +
                vehicleSold.getMake() + ", Model: " +
                vehicleSold.getModel() + ", Type: " +
                vehicleSold.getVehicleType() + ", Color: " +
                vehicleSold.getColor() + ", Odometer: " +
                vehicleSold.getOdometer() + ", Price: " +
                String.format("%.2f", vehicleSold.getPrice()) + ", Sales Tax: " +
                String.format("%.2f", SALES_TAX_AMOUNT) + ", Recording Fee: " +
                String.format("%.2f", RECORDING_FEE) + ", Processing Fee: " +
                String.format("%.2f", processingFee) + ", Financed: " +
                (isFinanced ? "YES" : "NO") + ", Total Price: " +
                String.format("%.2f", getTotalPrice()) + ", Monthly Payment: " +
                String.format("%.2f", getMonthlyPayment());
    }
    @Override
    public String toStringForFileWrite() {
        Vehicle vehicleSold = super.getVehicleSold();
        return "SALE|" + super.getDate() + "|" + super.getCustomerName() +
                "|" + super.getCustomerEmail() + "|" +
                vehicleSold.getVin() + "|" +
                vehicleSold.getYear() + "|" +
                vehicleSold.getMake() + "|" +
                vehicleSold.getModel() + "|" +
                vehicleSold.getVehicleType() + "|" +
                vehicleSold.getColor() + "|" +
                vehicleSold.getOdometer() + "|" +
                vehicleSold.getPrice() + "|" +
                SALES_TAX_AMOUNT + "|" +
                RECORDING_FEE + "|" +
                processingFee + "|" +
                (isFinanced ? "YES" : "NO") + "|" +
                getTotalPrice() + "|" +
                getMonthlyPayment();
    }
}
