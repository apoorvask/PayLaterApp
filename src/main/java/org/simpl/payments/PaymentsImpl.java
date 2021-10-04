package org.simpl.payments;

import org.simpl.constants.TransactionStatus;
import org.simpl.model.Merchant;
import org.simpl.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PaymentsImpl implements PaymentsInterface {

    Map<String, Merchant> merchantData = new HashMap<>();
    Map<String, User> userData = new HashMap<>();
    List<User> userList = new ArrayList<>();

    @Override
    public User addUser(String userName, String email, Double creditLimit) {
        User newUser = new User(userName, email, creditLimit);
        newUser.setDues(0D);
        userList.add(newUser);
        userData.put(userName, newUser);
        return newUser;
    }

    @Override
    public Merchant addMerchant(String merchantName, String email, Double discount) {
        Merchant newMerchant = new Merchant(merchantName, email, discount);
        newMerchant.setAllTransactionsSum(0D);
        merchantData.put(merchantName, newMerchant);
        return newMerchant;
    }

    @Override
    public TransactionStatus executeTransaction(String userName, String merchantName, Double transactionAmount) {
        User user = userData.get(userName);
        Double dues = user.getDues();

        if (dues + transactionAmount > user.getCreditLimit()) {
            return TransactionStatus.FAILURE;
        } else {
            user.setDues(dues + transactionAmount);
            Merchant merchant = merchantData.get(merchantName);
            merchant.setAllTransactionsSum(merchant.getAllTransactionsSum() + transactionAmount);
            merchantData.put(merchantName, merchant);
            userData.put(userName, user);
            userList.stream().filter(x -> x.getName().equals(userName)).map(x -> user);
            return TransactionStatus.SUCCESS;
        }
    }

    @Override
    public Merchant updateMerchant(String merchantName, String newDiscount) {
        Merchant merchant = merchantData.get(merchantName);
        merchant.setDiscountPercent(Double.parseDouble(newDiscount));
        merchantData.put(merchantName, merchant);
        return merchant;
    }

    @Override
    public User payBackUser(String userName, Double payBackAmount) {
        User user = userData.get(userName);
        user.setDues(user.getDues() - payBackAmount);
        userData.put(userName, user);
        userList.stream().filter(x -> x.getName().equals(userName)).map(x -> user);
        return user;
    }

    @Override
    public Double reportDiscount(String merchantName) {
        Merchant merchant = merchantData.get(merchantName);
        return (merchant.getAllTransactionsSum() * merchant.getDiscountPercent()) / 100;
    }

    @Override
    public Double reportDues(String userName) {
        return Math.abs(userData.get(userName).getDues());
    }

    @Override
    public List<String> reportUsersAtCreditLimit() {
        return userList.stream().filter(x -> Math.abs(x.getDues()) >= x.getCreditLimit()).map(x -> x.getName()).collect(Collectors.toList());
    }

    @Override
    public Double reportTotalDues() {
        Double total = 0D;
        for (User user : userList) {
            System.out.println(user.getName() + ": " + user.getDues());
            total += user.getDues();
        }
        return total;
    }
}
