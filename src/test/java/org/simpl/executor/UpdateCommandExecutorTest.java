package org.simpl.executor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.simpl.model.Merchant;
import org.simpl.payments.PaymentsInterface;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.simpl.constants.Constants.UPDATE;

@RunWith(MockitoJUnitRunner.class)
public class UpdateCommandExecutorTest {
    @Mock
    private PaymentsInterface paymentExecutor;

    @Test
    public void callUpdateMerchantFunctionWhenUpdateCommandIsEntered() {
        UpdateCommandExecutor executor = new UpdateCommandExecutor();
        Merchant m = new Merchant("m1", "m1@gmail.com", 2D);
        when(paymentExecutor.updateMerchant("m1", "2")).thenReturn(m);
        executor.executeCommand(UPDATE, new String[]{"update", "merchant", "m1", "2%"}, paymentExecutor);
        verify(paymentExecutor).updateMerchant("m1", "2");
    }
}
