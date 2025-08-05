package org.example.ui.tables;

import org.example.entity.Brand;
import org.example.entity.Product;
import org.example.entity.Shareholder;
import org.example.entity.ShareholderBrand;
import org.example.service.BrandService;
import org.example.service.ProductService;
import org.example.service.ShareholderBrandService;
import org.example.service.ShareholderService;
import org.example.ui.Printer;
import org.example.util.ApplicationContext;
import org.example.util.Constants;

import java.util.Scanner;

public class ShareholderBrandTable {
    static Scanner input = new Scanner(System.in);
    private static final ShareholderService shareholderService = ApplicationContext.getShareholderService();
    private static final BrandService brandService = ApplicationContext.getBrandService();
    private static final ShareholderBrandService shareholderBrandService = ApplicationContext.getShareholderBrandService();
    public static void shareholderBrandAccess() {
        int shareholderBrandItems;
        boolean breakLoop = false;

        while (!breakLoop) {
            Printer.printMenu(Constants.SHAREHOLDER_BRAND_ITEMS);
            shareholderBrandItems = input.nextInt();

            switch (shareholderBrandItems) {
                case 1 -> {
                    add();
                }
                case 2 -> {
                    readByBrand();
                }
                case 3 -> {
                    readByShareholder();
                }
                case 4 -> {
                    readAll();
                }
                case 5 -> {
                    breakLoop = true;
                    Printer.printMsg(Constants.ALERT_EXIT);
                }

                default -> Printer.printMsg(Constants.ALERT_CHOICE);
            }
        }

    }

    private static void add() {

        ShareholderBrand shareholderBrand = new ShareholderBrand();

        Printer.printMsg("choose your brand:");
        Brand[] brands = brandService.loadAllBrands();
        for (Brand element : brands) {
            System.out.print(element + "\n");
        }
        String brandName = input.nextLine();
        Brand brand = brandService.isExist(brandName);
        shareholderBrand.setBrand(brand);

        Printer.printMsg("choose your shareholder");
        Shareholder[] shareholders = shareholderService.loadAllShareholders();
        for (Shareholder element : shareholders) {
            System.out.print(element + "\n");
        }
        String shareholderName = input.nextLine();
        Shareholder shareholder = shareholderService.isExist(shareholderName);
        shareholderBrand.setShareholder(shareholder);

        shareholderBrandService.addRelation(shareholder,brand);

        Printer.printMsg("Your product successfully add:\n" + shareholderBrand);
    }

    private static void readByBrand() {
        Printer.printMsg("choose your brand:");
        Brand[] brands = brandService.loadAllBrands();
        for (Brand element : brands) {
            System.out.print(element + "\n");
        }
        String brandName = input.nextLine();
        Brand brand = brandService.isExist(brandName);
        ShareholderBrand[] shareholderBrands = shareholderBrandService.loadByBrand(brand);
        for(ShareholderBrand element: shareholderBrands) {
            System.out.print(element + "\n");
        }
    }

    private static void readByShareholder() {
        Printer.printMsg("choose your shareholder:");
        Shareholder[] shareholders = shareholderService.loadAllShareholders();
        for (Shareholder element : shareholders) {
            System.out.print(element + "\n");
        }
        String shareholderName = input.nextLine();
        Shareholder shareholder = shareholderService.isExist(shareholderName);
        ShareholderBrand[] shareholderBrands = shareholderBrandService.loadByShareholder(shareholder);
        for(ShareholderBrand element: shareholderBrands) {
            System.out.print(element + "\n");
        }
    }
    private static void readAll() {
        ShareholderBrand[] shareholderBrands = shareholderBrandService.loadAll();
        for (ShareholderBrand element : shareholderBrands) {
            System.out.print(element + "\n");
        }
    }
}
