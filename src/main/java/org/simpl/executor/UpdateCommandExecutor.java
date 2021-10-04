package org.simpl.executor;

import org.simpl.model.Merchant;
import org.simpl.payments.PaymentsInterface;

public class UpdateCommandExecutor implements CommandExecutor {

    @Override
    public void executeCommand(String keyword, String[] commandArray, PaymentsInterface paymentExecutor) {
        Merchant merchant = paymentExecutor.updateMerchant(commandArray[2], commandArray[3].replace("%", ""));
        System.out.println("interest-rate: " + merchant.getDiscountPercent() + "%");
    }
}
