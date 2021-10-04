package org.simpl.executor;

import org.simpl.payments.PaymentsInterface;

import java.util.List;

import static org.simpl.constants.Constants.*;

public class ReportCommandExecutor implements CommandExecutor {

    @Override
    public void executeCommand(String keyword, String[] commandArray, PaymentsInterface paymentExecutor) {

        switch (keyword) {
            case DISCOUNT:
                Double discount = paymentExecutor.reportDiscount(commandArray[2]);
                System.out.println(discount);
                break;
            case DUES:
                Double dues = paymentExecutor.reportDues(commandArray[2]);
                System.out.println(dues);
                break;
            case TOTAL_DUES:
                Double totalDues = paymentExecutor.reportTotalDues();
                System.out.println("total: " + totalDues);
                break;
            case USERS_AT_CREDIT_LIMIT:
                List<String> usersAtCreditLimit = paymentExecutor.reportUsersAtCreditLimit();
                System.out.println(String.join("\n", usersAtCreditLimit));
                break;
            default:
                System.out.println("Invalid Input. Press Enter to exit!");
                break;
        }

    }
}
