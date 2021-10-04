package org.simpl.executor;

import org.simpl.payments.PaymentsInterface;
import org.simpl.model.User;

public class PaybackCommandExecutor implements CommandExecutor {

    @Override
    public void executeCommand(String keyword, String[] commandArray, PaymentsInterface paymentExecutor) {
        User user = paymentExecutor.payBackUser(commandArray[1], Double.valueOf(commandArray[2]));
        System.out.println(user.getName() + "(dues: " + user.getDues() + ")");
    }
}
