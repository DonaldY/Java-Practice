package cn.donald.payroll;

import java.util.Date;
import java.util.Map;

/**
 * Created by DonaldY on 2017/7/5.
 */
public class CommissionClassification implements PaymentClassification {
    private double salary;
    private double rate;
    private Map<Date, SalesReceipt> salesReceiptMap;

    public CommissionClassification(double salary, double rate,
                                    Map<Date, SalesReceipt> salesReceiptMap) {
        this.salary = salary;
        this.rate = rate;
        this.salesReceiptMap = salesReceiptMap;
    }

    public void addSalesReceipt(SalesReceipt sr) {
        this.salesReceiptMap.put(sr.getSaleDate(), sr);
    }

    @Override
    public double calculatePay(Paycheck pc) {
        double commission = 0;
        for (SalesReceipt sr : this.salesReceiptMap.values()) {
            if (DateUtil.between(sr.getSaleDate(), pc.getPayPeriodStartDate(),
                pc.getPayPeriodEndDate())) {
                commission += sr.getAmount() * this.rate;
            }

        }
        return this.salary + commission;
    }
}
