package org.example.ui.tables;

import org.example.entity.Shareholder;
import org.example.service.ShareholderService;
import org.example.ui.Printer;
import org.example.util.ApplicationContext;
import org.example.util.Constants;

import java.util.Scanner;

public class ShareholderTable {
    static Scanner input = new Scanner(System.in);
    private static final ShareholderService shareholderService = ApplicationContext.getShareholderService();

    public static void shareholderAccess() {
        int shareholderItems;
        boolean breakLoop = false;

        while (!breakLoop) {
            Printer.printMenu(Constants.TABLES_ITEMS);
            shareholderItems = input.nextInt();

            switch (shareholderItems) {
                case 1 -> {
                    addShareholder();
                }
                case 2 -> {
                    updateShareholder();
                }
                case 3 -> {
                    deleteShareholder();
                }
                case 4 -> {
                    breakLoop = true;
                    Printer.printMsg(Constants.ALERT_EXIT);
                }

                default -> Printer.printMsg(Constants.ALERT_CHOICE);
            }
        }

    }

    private static void addShareholder() {
        Shareholder shareholder = new Shareholder();

        System.out.println("Enter Your Shareholder Name: ");
        shareholder.setShareholderName(input.nextLine());

        System.out.println("Enter phone number: ");
        shareholder.setPhoneNumber(input.nextLong());

        System.out.println("Enter nationality: ");
        shareholder.setNationalCode(input.nextLong());

        if (shareholderService.addNewShareholder(shareholder) > 0 ){
            Printer.printMsg("Shareholder added successfully");
        } else {
            System.out.println("invalid input");
        }

    }

    private static void updateShareholder() {
        Shareholder shareholder = new Shareholder();

        System.out.println("Enter Your Shareholder Name: ");
        shareholder.setShareholderName(input.nextLine());

        System.out.println("Enter phone number: ");
        shareholder.setPhoneNumber(input.nextLong());

        System.out.println("Enter nationality: ");
        shareholder.setNationalCode(input.nextLong());

        if (shareholderService.addNewShareholder(shareholder) > 0 ){
            Printer.printMsg(shareholderService.updateShareholder(shareholder) + " rows have effected.");
        } else {
            System.out.println("invalid input");
        }

    }

    private static void deleteShareholder() {
        Shareholder shareholder = new Shareholder();

        System.out.println("Enter Your Shareholder Name: ");
        shareholder.setShareholderName(input.nextLine());

        System.out.println("Enter phone number: ");
        shareholder.setPhoneNumber(input.nextLong());

        System.out.println("Enter nationality: ");
        shareholder.setNationalCode(input.nextLong());

        if (shareholderService.addNewShareholder(shareholder) > 0 ){
            Printer.printMsg(shareholderService.deleteShareholder(shareholder) + " rows have deleted.");
        } else {
            System.out.println("invalid input");
        }

    }

}
