package cn.donald.payroll;

/**
 * Created by DonaldY on 2017/7/5.
 */
public class SalariedClassification implements PaymentClassification {
    private double salary;

    public SalariedClassification(double salary) {
        this.salary = salary;
    }

    @Override
    public double calculatePay(Paycheck pc) {
        return this.salary;
    }
}
