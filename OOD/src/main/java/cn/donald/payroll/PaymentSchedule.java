package cn.donald.payroll;

import java.util.Date;

/**
 * Created by DonaldY on 2017/7/4.
 */
public interface PaymentSchedule {
    public boolean isPayDate(Date date);
    public Date getPayPeriodStartDate( Date payPeriodEndDate);
}
