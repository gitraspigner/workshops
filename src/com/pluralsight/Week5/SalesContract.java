package com.pluralsight.Week5;
/**
 * *******Add program description here******
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
}
