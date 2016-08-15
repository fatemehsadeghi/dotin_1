package calculateDeposit.Exception;

import sun.plugin2.message.Message;

/**
 * Created by fateme on 13/08/2016.
 */
public class DurationInDaysCantBeNegativeAndZeroException extends Exception {

    public DurationInDaysCantBeNegativeAndZeroException(String message) {
        super(message);
    }
}

