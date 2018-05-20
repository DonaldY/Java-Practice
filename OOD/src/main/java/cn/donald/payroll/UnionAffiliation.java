package cn.donald.payroll;

/**
 * Created by DonaldY on 2017/7/5.
 */
public class UnionAffiliation implements Affiliation {

    private int memberID;
    private double weeklyDue;
    private ServerCharge serverCharges;

    @Override
    public double calculateDeductions(Paycheck pc) {

        return 0;
    }
}
