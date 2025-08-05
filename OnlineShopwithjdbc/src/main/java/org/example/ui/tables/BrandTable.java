package org.example.ui.tables;

import org.example.entity.Brand;
import org.example.service.BrandService;
import org.example.service.ProductService;
import org.example.ui.Printer;
import org.example.util.ApplicationContext;
import org.example.util.Constants;

import java.util.Scanner;

public class BrandTable {
    static Scanner input = new Scanner(System.in);
    private static final BrandService brandService = ApplicationContext.getBrandService();
    private static final ProductService productService = ApplicationContext.getProductService();

    public static void brandAccess() {
        int brandItems;
        boolean breakLoop = false;

        while (!breakLoop) {
            Printer.printMenu(Constants.TABLES_ITEMS);
            brandItems = input.nextInt();

            switch (brandItems) {
                case 1 -> {
                    addBrand();
                }
                case 2 -> {
                    updateBrand();
                }
                case 3 -> {
                    deleteBrand();
                }
                case 4 -> {
                    breakLoop = true;
                    Printer.printMsg(Constants.ALERT_EXIT);
                }

                default -> Printer.printMsg(Constants.ALERT_CHOICE);
            }
        }

    }

    private static void addBrand() {
        Brand brand = new Brand();

        System.out.println("Enter Your Brand Name: ");
        brand.setBrandName(input.nextLine());

        System.out.println("Enter Your Website: ");
        brand.setWebsite(input.nextLine());

        System.out.println("Enter Your description: ");
        brand.setDescription(input.nextLine());

        String msg = brandService.addNewBrand(brand);
        Printer.printMsg(msg);
    }

    private static void updateBrand() {
        Brand brand = new Brand();

        System.out.println("Enter Your Brand Name: ");
        brand.setBrandName(input.nextLine());

        System.out.println("Enter Your Website: ");
        brand.setWebsite(input.nextLine());

        System.out.println("Enter Your description: ");
        brand.setDescription(input.nextLine());

        Printer.printMsg(brandService.updateBrand(brand) + " rows have effected.");
    }

    private static void deleteBrand() {
        Brand brand = new Brand();

        System.out.println("Enter Your Brand Name: ");
        brand.setBrandName(input.nextLine());

        System.out.println("Enter Your Website: ");
        brand.setWebsite(input.nextLine());

        System.out.println("Enter Your description: ");
        brand.setDescription(input.nextLine());

        if (productService.findByBrand(brand.getBrandID()) != null) {
            System.out.println("The related product is deleted.");
            productService.deleteProduct(productService.findByBrand(brand.getBrandID()));
        }
        System.out.println(brandService.deleteBrand(brand) + " have been deleted");
    }
}
