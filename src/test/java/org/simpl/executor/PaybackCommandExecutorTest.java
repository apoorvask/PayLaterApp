package org.simpl.executor;

import org.simpl.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.simpl.payments.PaymentsInterface;

import static org.simpl.constants.Constants.PAYBACK;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PaybackCommandExecutorTest {
    @Mock
    private PaymentsInterface paymentExecutor;

    @Test
    public void callPaybackUserFunctionWhenPaybackCommandIsEntered() {
        PaybackCommandExecutor executor = new PaybackCommandExecutor();
        User u = new User("u1", "u1@gmail.com", Double.valueOf("1000"));
        when(paymentExecutor.payBackUser("u1", Double.valueOf("1000"))).thenReturn(u);
        executor.executeCommand(PAYBACK, new String[]{"payback", "u1", "1000"}, paymentExecutor);
        verify(paymentExecutor).payBackUser("u1", Double.valueOf("1000"));
    }
}
