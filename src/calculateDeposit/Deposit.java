package calculateDeposit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fateme on 05/08/2016.
 */
public class Deposit {
    protected BigDecimal payedInterest;
    protected BigDecimal depositeBalance;
   // protected BigDecimal interestRate;
    protected BigDecimal durationDays;
    protected BigDecimal time=new BigDecimal("36500");

    public BigDecimal calculateInterest(BigDecimal payedInterest, BigDecimal depositeBalance, BigDecimal interestRate, BigDecimal durationDays, BigDecimal time) {
        payedInterest = payedInterest.multiply(interestRate);
        payedInterest = payedInterest.multiply(depositeBalance);
        payedInterest = payedInterest.multiply(durationDays);
        payedInterest = payedInterest.divide(time);
        return payedInterest;
    }

    public static void main(String[] args) {
        //BigDecimal Pi = new BigDecimal();
        System.out.println("salam");
        Deposit d = new Deposit();
        List<Deposit> deposits = new ArrayList<Deposit>();
    }
}
