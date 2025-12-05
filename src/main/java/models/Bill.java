package models;

import java.util.Date;

public class Bill {
    private int billId;
    private double totalAmount;
    private Date generatedDate;

    public Bill(int billId, double totalAmount, Date generatedDate) {
        this.billId = billId;
        this.totalAmount = totalAmount;
        this.generatedDate = generatedDate;
    }

    public Bill() {

    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getGeneratedDate() {
        return generatedDate;
    }

    public void setGeneratedDate(Date generatedDate) {
        this.generatedDate = generatedDate;
    }

    public double calculateTotal() {
        return 0.0;
    }
}
