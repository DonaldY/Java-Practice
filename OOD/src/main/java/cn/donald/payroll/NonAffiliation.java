package cn.donald.payroll;

/**
 * Created by DonaldY on 2017/7/5.
 */
public class NonAffiliation implements Affiliation {
    @Override
    public double calculateDeductions(Paycheck pc) {
        return 0.0;
    }
}
