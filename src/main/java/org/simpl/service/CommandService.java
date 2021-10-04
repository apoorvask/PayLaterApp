package org.simpl.service;

import org.simpl.payments.PaymentsImpl;
import org.simpl.executor.CreateCommandExecutor;
import org.simpl.executor.ReportCommandExecutor;
import org.simpl.executor.UpdateCommandExecutor;
import org.simpl.payments.PaymentsInterface;
import org.simpl.executor.PaybackCommandExecutor;

import static org.simpl.constants.Constants.*;

public class CommandService {

    PaymentsInterface paymentExecutor = new PaymentsImpl();

    public void runCommand(String command) {
        String[] commandArray = command.split(" ");
        String firstWord = commandArray[0];

        switch (firstWord) {
            case NEW:
                CreateCommandExecutor createCommandSelector = new CreateCommandExecutor();
                createCommandSelector.executeCommand(commandArray[1], commandArray, paymentExecutor);
                break;
            case UPDATE:
                UpdateCommandExecutor updateCommandSelector = new UpdateCommandExecutor();
                updateCommandSelector.executeCommand(commandArray[1], commandArray, paymentExecutor);
                break;
            case PAYBACK:
                PaybackCommandExecutor paybackCommandSelector = new PaybackCommandExecutor();
                paybackCommandSelector.executeCommand(commandArray[1], commandArray, paymentExecutor);
                break;
            case REPORT:
                ReportCommandExecutor reportCommandSelector = new ReportCommandExecutor();
                reportCommandSelector.executeCommand(commandArray[1], commandArray, paymentExecutor);
                break;
            default:
                System.out.println("Invalid Input. Press Enter to exit!");
                break;
        }
    }
}
