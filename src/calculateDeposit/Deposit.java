package calculateDeposit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fateme on 05/08/2016.
 */
public class Deposit {
    protected String customerNumber;
    protected BigDecimal payedInterest;
    protected BigDecimal depositBalance;
    // protected BigDecimal interestRate;
    protected int durationInDays;
    protected int time = 36500;
    DepositType depositType;

    public Deposit(String cn, BigDecimal pi, BigDecimal db) {
        this.customerNumber = cn;
        this.payedInterest = pi;
        this.depositBalance = db;

    }

    public Deposit(String customerNumber, BigDecimal payedInterest, BigDecimal depositBalance, int durationInDays) {
    }

    public Deposit() {

    }


    public BigDecimal calculateInterest(BigDecimal payedInterest, BigDecimal depositeBalance, int interestRate, int durationInDays, int time) {
        payedInterest = payedInterest.multiply(depositeBalance);
        payedInterest = payedInterest.multiply(new BigDecimal(interestRate));
        payedInterest = payedInterest.multiply(new BigDecimal(durationInDays));
        payedInterest = payedInterest.divide(new BigDecimal(time));
        return payedInterest;
    }

    public static void main(String[] args) {
        //BigDecimal Pi = new BigDecimal();
        System.out.println("salam");
        Deposit d = new Deposit();
        List<Deposit> deposits = new ArrayList<Deposit>();
    }
}
