package calculateDeposit;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Deposit implements Comparable<Deposit> {
    private String customerNumber;
    private BigDecimal payedInterest = new BigDecimal(1);
    private BigDecimal depositBalance;
    private int durationInDays;
    private DepositType depositType;
    final int TIME = 36500;

    public Deposit(String customerNumber, BigDecimal depositBalance, int durationInDays, DepositType depositType) {
        this.customerNumber = customerNumber;
        this.depositType = depositType;
        this.durationInDays = durationInDays;
        this.depositBalance = depositBalance;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String name) {
        this.customerNumber = customerNumber;
    }

    public DepositType getDepositType() {
        return depositType;
    }

    public void setDepositType(DepositType depositType) {
        this.customerNumber = customerNumber;
    }

    public BigDecimal getPayedInterest() {
        return payedInterest;
    }

    public void setPayedInterest(BigDecimal payedInterest) {
        this.payedInterest = payedInterest;
    }

    public BigDecimal getDepositBalance() {
        return depositBalance;
    }

    public void setDepositBalance(BigDecimal depositBalance) {
        this.depositBalance = depositBalance;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
    }


    public void calculateInterest(BigDecimal depositBalance, int interestRate, int durationInDays, int TIME) {
        payedInterest = depositBalance.multiply(new BigDecimal(interestRate)).multiply(new BigDecimal(durationInDays))
                .divide(new BigDecimal(TIME), 5, RoundingMode.HALF_UP);
//        System.out.printf("Deposit Balance: %f, Duration: %d, Deposit Type: %s, Paid Interest: %f\n", depositBalance, durationInDays, depositType.getClass().getName(), payedInterest);
    }

    public static void main(String[] args) {

    }

    public int compareTo(Deposit deposit) {

        return -1 * payedInterest.compareTo(deposit.getPayedInterest());
    }
}