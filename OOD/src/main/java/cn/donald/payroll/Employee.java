package cn.donald.payroll;

import java.util.Date;

/**
 * Created by DonaldY on 2017/7/4.
 */
public class Employee {
    String id;
    String name;
    String address;
    Affiliation affiliation;


    PaymentClassification classification;
    PaymentSchedule schedule;
    PaymentMethod paymentMethod;

    public Employee(String name, String address){
        this.name = name;
        this.address = address;
    }
    public boolean isPayDay(Date d) {
        return this.schedule.isPayDate(d);
    }

    public Date getPayPeriodStartDate(Date d) {
        return this.schedule.getPayPeriodStartDate(d);
    }

    public void payDay(Paycheck pc){
        double grossPay = this.classification.calculatePay(pc);
        double deductionPay = this.affiliation.calculateDeductions(pc);
        double netPay = grossPay - deductionPay;
        pc.setDeductions(deductionPay);
        pc.setGrossPay(grossPay);
        pc.setNetPay(netPay);
        this.paymentMethod.pay(pc);
    }

    public void setClassification(PaymentClassification classification) {
        this.classification = classification;
    }

    public void setSchedule(PaymentSchedule schedule) {
        this.schedule = schedule;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
