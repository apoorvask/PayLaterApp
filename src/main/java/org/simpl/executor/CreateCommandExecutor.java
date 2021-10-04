package org.simpl.executor;

import org.simpl.constants.TransactionStatus;
import org.simpl.model.Merchant;
import org.simpl.model.User;
import org.simpl.payments.PaymentsInterface;

import static org.simpl.constants.Constants.*;

public class CreateCommandExecutor implements CommandExecutor {

    @Override
    public void executeCommand(String keyword, String[] commandArray, PaymentsInterface paymentExecutor) {
        switch (keyword) {
            case USER:
                User user = paymentExecutor.addUser(commandArray[2], commandArray[3], Double.valueOf(commandArray[4]));
                System.out.println(user.getName() + " (" + user.getCreditLimit() + ")");
                break;
            case MERCHANT:
                Merchant merchant = paymentExecutor.addMerchant(commandArray[2], commandArray[3], Double.valueOf(commandArray[4].replace("%", "")));
                System.out.println(merchant.getName() + " (" + merchant.getDiscountPercent() + "%)");
                break;
            case TXN:
                TransactionStatus transactionStatus = paymentExecutor.executeTransaction(commandArray[2], commandArray[3], Double.valueOf(commandArray[4]));
                if (transactionStatus == TransactionStatus.FAILURE)
                    System.out.println("rejected! (reason: credit limit)");
                else System.out.println("success!");
                break;
            default:
                System.out.println("Invalid Input. Press Enter to exit!");
                break;
        }
    }
}
