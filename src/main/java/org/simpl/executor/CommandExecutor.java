package org.simpl.executor;

import org.simpl.payments.PaymentsInterface;

public interface CommandExecutor {
    void executeCommand(String keyword, String[] commandArray, PaymentsInterface paymentExecutor);
}
