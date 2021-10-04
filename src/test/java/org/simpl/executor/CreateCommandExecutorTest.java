package org.simpl.executor;

import org.simpl.constants.TransactionStatus;
import org.simpl.model.Merchant;
import org.simpl.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.simpl.payments.PaymentsInterface;

import static org.simpl.constants.Constants.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateCommandExecutorTest {

    @Mock
    private PaymentsInterface paymentExecutor;

    @Test
    public void callAddUserFunctionWhenUserCommandIsEntered() {
        CreateCommandExecutor executor = new CreateCommandExecutor();
        User u = new User("u1", "u1@gmail.com", Double.valueOf("1000"));
        when(paymentExecutor.addUser("u1", "u1@gmail.com", Double.valueOf("1000"))).thenReturn(u);
        executor.executeCommand(USER, new String[]{"new", "user", "u1", "u1@gmail.com", "1000"}, paymentExecutor);
        verify(paymentExecutor).addUser("u1", "u1@gmail.com", Double.valueOf("1000"));
    }

    @Test
    public void callAddMerchantFunctionWhenMerchantCommandIsEntered() {
        CreateCommandExecutor executor = new CreateCommandExecutor();
        Merchant m = new Merchant("m1", "m1@gmail.com", 1.5D);
        when(paymentExecutor.addMerchant("m1", "m1@gmail.com", 1.5D)).thenReturn(m);
        executor.executeCommand(MERCHANT, new String[]{"new", "merchant", "m1", "m1@gmail.com", "1.5"}, paymentExecutor);
        verify(paymentExecutor).addMerchant("m1", "m1@gmail.com", 1.5D);
    }

    @Test
    public void callGetTransactionStatusFunctionWhenTxnCommandIsEntered() {
        CreateCommandExecutor executor = new CreateCommandExecutor();
        when(paymentExecutor.executeTransaction("u1", "m1", 1000D)).thenReturn(TransactionStatus.SUCCESS);
        executor.executeCommand(TXN, new String[]{"new", "txn", "u1", "m1", "1000"}, paymentExecutor);
        verify(paymentExecutor).executeTransaction("u1", "m1", 1000D);
    }

    @Test
    public void callGetTransactionStatusFailureCaseForInvalidTransactions() {
        CreateCommandExecutor executor = new CreateCommandExecutor();
        when(paymentExecutor.executeTransaction("u1", "m1", 1000D)).thenReturn(TransactionStatus.FAILURE);
        executor.executeCommand(TXN, new String[]{"new", "txn", "u1", "m1", "1000"}, paymentExecutor);
        verify(paymentExecutor).executeTransaction("u1", "m1", 1000D);
    }
}
