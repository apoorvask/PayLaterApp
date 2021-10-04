package org.simpl.payments;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.simpl.constants.TransactionStatus;
import org.simpl.model.Merchant;
import org.simpl.model.User;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PaymentsImplTest {

    PaymentsInterface paymentsExecutor = new PaymentsImpl();

    @Test
    public void addUserShouldReturnNewUser() {
        User u = paymentsExecutor.addUser("u1", "u1.gmail.com", 500D);
        User expectedUser = new User("u1", "u1@gmail.com", Double.valueOf("500"));
        assert u.getName().equals(expectedUser.getName());
    }

    @Test
    public void addMerchantShouldReturnNewMerchant() {
        Merchant m = paymentsExecutor.addMerchant("m1", "m1.gmail.com", 1.5D);
        Merchant expectedMerchant = new Merchant("m1", "m1@gmail.com", 1.5D);
        assert m.getName().equals(expectedMerchant.getName());
    }

    @Test
    public void updateMerchantShouldReturnUpdatedMerchant() {
        Merchant m = paymentsExecutor.addMerchant("m1", "m1.gmail.com", 1.5D);
        Merchant updatedMerchant = paymentsExecutor.updateMerchant("m1", "2");
        Merchant expectedMerchant = new Merchant("m1", "m1@gmail.com", 2D);
        assert updatedMerchant.getName().equals(expectedMerchant.getName());
    }

    @Test
    public void getTxnStatusShouldReturnSuccessStatus() {
        User u = paymentsExecutor.addUser("u1", "u1.gmail.com", 500D);
        Merchant m = paymentsExecutor.addMerchant("m1", "m1.gmail.com", 1.5D);
        TransactionStatus status = paymentsExecutor.executeTransaction("u1", "m1", 300D);
        assert status.equals(TransactionStatus.SUCCESS);
    }

    @Test
    public void getTxnStatusShouldReturnFailureStatus() {
        User u = paymentsExecutor.addUser("u1", "u1.gmail.com", 500D);
        Merchant m = paymentsExecutor.addMerchant("m1", "m1.gmail.com", 1.5D);
        TransactionStatus status = paymentsExecutor.executeTransaction("u1", "m1", 600D);
        assert status.equals(TransactionStatus.FAILURE);
    }

    @Test
    public void payBackUserShouldReturnCorrectPaybackAmount() {
        User u = paymentsExecutor.addUser("u1", "u1.gmail.com", 600D);
        u.setDues(300D);
        User updatedUser = paymentsExecutor.payBackUser("u1", 100D);
        assert updatedUser.getDues().equals(200D);
    }

    @Test
    public void reportDuesShouldReturnCorrectDuesAmount() {
        User u1 = paymentsExecutor.addUser("u1", "u1.gmail.com", 600D);
        u1.setDues(500D);
        Double dues = paymentsExecutor.reportDues("u1");
        assert dues.equals(500D);
    }

    @Test
    public void reportUsersAtCreditLimitShouldReturnListOfUsers() {
        User u1 = paymentsExecutor.addUser("u1", "u1.gmail.com", 600D);
        u1.setDues(600D);
        User u2 = paymentsExecutor.addUser("u2", "u2.gmail.com", 300D);
        u2.setDues(400D);
        User u3 = paymentsExecutor.addUser("u3", "u3.gmail.com", 100D);
        u3.setDues(50D);
        List<String> users = paymentsExecutor.reportUsersAtCreditLimit();
        List<String> expectedUsers = new ArrayList<>();
        expectedUsers.add("u1");
        expectedUsers.add("u2");
        assert users.equals(expectedUsers);
    }

    @Test
    public void reportTotalDuesShouldReturnCorrectTotalAmount() {
        User u1 = paymentsExecutor.addUser("u1", "u1.gmail.com", 600D);
        u1.setDues(500D);
        User u2 = paymentsExecutor.addUser("u2", "u2.gmail.com", 300D);
        u2.setDues(100D);
        User u3 = paymentsExecutor.addUser("u3", "u3.gmail.com", 100D);
        u3.setDues(50D);
        Double totalDues = paymentsExecutor.reportTotalDues();
        assert totalDues.equals(650D);
    }

    @Test
    public void reportDiscountShouldReturnCorrectDiscount() {
        Merchant m = paymentsExecutor.addMerchant("m", "m.gmail.com", 1.5D);
        m.setAllTransactionsSum(0D);
        User u1 = paymentsExecutor.addUser("u1", "u1.gmail.com", 600D);
        u1.setDues(0D);
        User u2 = paymentsExecutor.addUser("u2", "u2.gmail.com", 300D);
        u2.setDues(0D);
        User u3 = paymentsExecutor.addUser("u3", "u3.gmail.com", 100D);
        u3.setDues(0D);
        paymentsExecutor.executeTransaction("u1", "m", 300D);
        paymentsExecutor.executeTransaction("u2", "m", 200D);
        paymentsExecutor.executeTransaction("u3", "m", 50D);

        Double discount = paymentsExecutor.reportDiscount("m");
        assert discount.equals(8.25D);
    }
}
