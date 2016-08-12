package calculateDeposit;
import java.math.BigDecimal;
import java.math.RoundingMode;
public class Deposit {
    private String customerNumber="20";
    private BigDecimal payedInterest = new BigDecimal(1);
    private BigDecimal depositBalance;
    private int durationInDays;
    private DepositType depositType  ;
    final int TIME = 36500;
    //DepositType interestRate ;

    public Deposit(String customerNumber, BigDecimal depositBalance, int durationInDays, DepositType depositType , BigDecimal payedInterest ) {
        this.customerNumber = customerNumber;
        this.depositType =  depositType;
        this.durationInDays = durationInDays;
        this.depositBalance = depositBalance;
        this.payedInterest = payedInterest;
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
        payedInterest = payedInterest.multiply(depositBalance);
        payedInterest = payedInterest.multiply(new BigDecimal(interestRate));
        payedInterest = payedInterest.multiply(new BigDecimal(durationInDays));
        //System.out.println(payedInterest);
        payedInterest = payedInterest.divide(new BigDecimal(TIME), 20, RoundingMode.HALF_UP);
        System.out.println(payedInterest);

    }

    public static void main(String[] args) {
        //BigDecimal Pi = new BigDecimal();
//        System.out.println("salam");
//        Deposit d = new Deposit();
//        List<Deposit> deposits = new ArrayList<Deposit>();
    }
}
