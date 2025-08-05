package org.example.ui;

import org.example.ui.tables.*;
import org.example.util.Constants;

import java.util.Scanner;

public class Menu {
    static Scanner input = new Scanner(System.in);


    public static void run() {
        int runItem;
        boolean breakLoop = false;

        while (!breakLoop) {
            Printer.printMenu(Constants.RUN_ITEMS);
            runItem = input.nextInt();

            switch (runItem) {
                case 1 -> {
                    UserActivity.signup();
                }
                case 2 -> {
                    if(UserActivity.login() != null) {
                        Printer.printMsg(UserActivity.login() + Constants.ALERT_USER_LOGIN);
                        String loggedIn = input.nextLine();
                        accessDatabase(loggedIn);
                    }
                }
                case 3 -> {
                    breakLoop = true;
                    Printer.printMsg(Constants.ALERT_EXIT);
                }

                default -> Printer.printMsg(Constants.ALERT_CHOICE);
            }
        }


    }

    private static void accessDatabase(String access) {
        if (access.equalsIgnoreCase("y")) {
            int databaseItem;
            boolean breakLoop = false;

            while (!breakLoop) {
                Printer.printMenu(Constants.DATABASE_ITEMS);
                databaseItem = input.nextInt();

                switch (databaseItem) {
                    case 1 -> BrandTable.brandAccess();
                    case 2 -> CategoryTable.categoryAccess();
                    case 3 -> ProductTable.productAccess();
                    case 4 -> ShareholderTable.shareholderAccess();
                    case 5 -> ShareholderBrandTable.shareholderBrandAccess();
                    case 6 -> {
                        breakLoop = true;
                        Printer.printMsg(Constants.ALERT_EXIT);
                    }
                    default -> Printer.printMsg(Constants.ALERT_CHOICE);
                }

            }

        }
    }

}
