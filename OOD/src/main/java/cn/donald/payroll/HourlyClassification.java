package cn.donald.payroll;

import java.util.Date;
import java.util.Map;

/**
 * Created by DonaldY on 2017/7/5.
 */
public class HourlyClassification implements PaymentClassification {
    private double rate;
    private Map<Date, TimeCard> timeCardMap;

    public void addTimeCard(TimeCard tc) {
        this.timeCardMap.put(tc.getDate(), tc);
    }

    @Override
    public double calculatePay(Paycheck pc) {
        double totalPay = 0;
        for (TimeCard tc : this.timeCardMap.values()) {
            if (DateUtil.between(tc.getDate(), pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate())) {
                totalPay += calculatePayForTimeCard(tc);
            }
        }
        return totalPay;
    }

    private double calculatePayForTimeCard(TimeCard tc) {
        int hours = tc.getHours();
        if (hours > 8) {
            return 8 * this.rate + (hours - 8) * this.rate * 1.5;
        } else {
            return 8 * this.rate;
        }
    }
}
