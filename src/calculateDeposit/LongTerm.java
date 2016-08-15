package calculateDeposit;

import calculateDeposit.Exception.DurationInDaysCantBeNegativeAndZeroException;

/**
 * Created by fateme on 06/08/2016.
 */
public class LongTerm extends DepositType {
    public LongTerm() {
        super.interestRate = 20;
    }

}
