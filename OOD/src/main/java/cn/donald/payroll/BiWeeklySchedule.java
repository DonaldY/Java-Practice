package cn.donald.payroll;

import java.util.Date;

/**
 * Created by DonaldY on 2017/7/5.
 */
public class BiWeeklySchedule implements PaymentSchedule{

    Date firstPayableFriday = DateUtil.parseDate("2017-7-5");

    @Override
    public boolean isPayDate(Date date) {
        long interval = DateUtil.getDaysBetween(date, this.firstPayableFriday);
        return interval % 14 == 0;
    }

    @Override
    public Date getPayPeriodStartDate(Date payPeriodEndDate) {
        return DateUtil.add(payPeriodEndDate, -13);
    }
}
