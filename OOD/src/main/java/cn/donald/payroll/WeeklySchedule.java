package cn.donald.payroll;

import java.util.Date;

/**
 * Created by DonaldY on 2017/7/5.
 */
public class WeeklySchedule implements PaymentSchedule {

    @Override
    public boolean isPayDate(Date date) {
        return DateUtil.isFriday(date);
    }

    @Override
    public Date getPayPeriodStartDate(Date payPeriodEndDate) {
        return DateUtil.add(payPeriodEndDate, -6);
    }
}
