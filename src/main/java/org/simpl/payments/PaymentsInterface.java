package org.simpl.payments;

import org.simpl.constants.TransactionStatus;
import org.simpl.model.Merchant;
import org.simpl.model.User;

import java.util.List;

public interface PaymentsInterface {
    User addUser(String userName, String email, Double creditLimit);

    Merchant addMerchant(String merchantName, String email, Double discount);

    TransactionStatus executeTransaction(String userName, String merchantName, Double transactionAmount);

    Merchant updateMerchant(String merchantName, String newDiscount);

    User payBackUser(String userName, Double payBackAmount);

    Double reportDiscount(String merchantName);

    Double reportDues(String userName);

    List<String> reportUsersAtCreditLimit();

    Double reportTotalDues();
}
