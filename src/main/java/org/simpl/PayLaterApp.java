package org.simpl;

import org.simpl.service.CommandService;

import java.util.Scanner;

public class PayLaterApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandService service = new CommandService();

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (!command.isEmpty())
                service.runCommand(command);
            else return;
        }
    }
}
