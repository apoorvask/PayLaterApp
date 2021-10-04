package org.simpl.executor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.simpl.payments.PaymentsInterface;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.simpl.constants.Constants.*;

@RunWith(MockitoJUnitRunner.class)
public class ReportCommandExecutorTest {

    @Mock
    private PaymentsInterface paymentExecutor;

    @Test
    public void callReportDiscountFunctionWhenDiscountCommandIsEntered() {
        ReportCommandExecutor executor = new ReportCommandExecutor();
        when(paymentExecutor.reportDiscount("m1")).thenReturn(5D);
        executor.executeCommand(DISCOUNT, new String[]{"report", "discount", "m1"}, paymentExecutor);
        verify(paymentExecutor).reportDiscount("m1");
    }

    @Test
    public void callReportDuesFunctionWhenDuesCommandIsEntered() {
        ReportCommandExecutor executor = new ReportCommandExecutor();
        when(paymentExecutor.reportDues("u1")).thenReturn(500D);
        executor.executeCommand(DUES, new String[]{"report", "dues", "u1"}, paymentExecutor);
        verify(paymentExecutor).reportDues("u1");
    }

    @Test
    public void callReportTotalDuesFunctionWhenTotalDuesCommandIsEntered() {
        ReportCommandExecutor executor = new ReportCommandExecutor();
        when(paymentExecutor.reportTotalDues()).thenReturn(500D);
        executor.executeCommand(TOTAL_DUES, new String[]{"report", "total-dues"}, paymentExecutor);
        verify(paymentExecutor).reportTotalDues();
    }

    @Test
    public void callReportUsersAtCreditLimitFunctionWhenCreditLimitCommandIsEntered() {
        ReportCommandExecutor executor = new ReportCommandExecutor();
        List<String> users = new ArrayList<>();
        users.add("u1");
        users.add("u2");
        when(paymentExecutor.reportUsersAtCreditLimit()).thenReturn(users);
        executor.executeCommand(USERS_AT_CREDIT_LIMIT, new String[]{"report", "users_at_credit_limit"}, paymentExecutor);
        verify(paymentExecutor).reportUsersAtCreditLimit();
    }
}
