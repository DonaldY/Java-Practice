package cn.donald.payroll;

import java.util.Date;

/**
 * Created by DonaldY on 2017/7/4.
 */
public class MonthlySchedule implements PaymentSchedule {
    @Override
    public boolean isPayDate(Date date) {
        return DateUtil.isLastOfMonth(date);
    }

    @Override
    public Date getPayPeriodStartDate(Date payPeriodEndDate) {

        return DateUtil.getFirstDay(payPeriodEndDate);
    }
}
