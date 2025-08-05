package org.example.ui.tables;

import org.example.entity.Brand;
import org.example.entity.Category;
import org.example.entity.Product;
import org.example.service.BrandService;
import org.example.service.CategoryService;
import org.example.service.ProductService;
import org.example.ui.Printer;
import org.example.util.ApplicationContext;
import org.example.util.Constants;

import java.util.Date;
import java.util.Scanner;

public class ProductTable {
    static Scanner input = new Scanner(System.in);
    private static final ProductService productService = ApplicationContext.getProductService();
    private static final CategoryService categoryService = ApplicationContext.getCategoryService();
    private static final BrandService brandService = ApplicationContext.getBrandService();


    public static void productAccess() {
        int productItems;
        boolean breakLoop = false;

        while (!breakLoop) {
            Printer.printMenu(Constants.TABLES_ITEMS);
            productItems = input.nextInt();

            switch (productItems) {
                case 1 -> {
                    addProduct();
                }
                case 2 -> {
                    updateProduct();
                }
                case 3 -> {
                    deleteProduct();
                }
                case 4 -> {
                    breakLoop = true;
                    Printer.printMsg(Constants.ALERT_EXIT);
                }

                default -> Printer.printMsg(Constants.ALERT_CHOICE);
            }
        }

    }

    private static void addProduct() {
        if (categoryService.recordCounter() < 1 && brandService.recordCounter() < 1){
            System.out.println("first add brand and category");
            return;
        }
        Product product = new Product();

        Printer.printMsg("choose your brand:");
        Brand[] brands = brandService.loadAllBrands();
        for (Brand element : brands) {
            System.out.print(element + "\n");
        }
        String brandName = input.nextLine();
        Brand brand = brandService.isExist(brandName);

        Printer.printMsg("choose your category");
        Category[] categories = categoryService.loadAllCategories();
        for (Category element : categories) {
            System.out.print(element + "\n");
        }
        String categoryName = input.nextLine();
        Category category = categoryService.isExist(categoryName);

        System.out.println("product name");
        product.setProductName(input.nextLine());

        product.setBrandID(brand.getBrandID());
        product.setCategoryID(category.getCategoryID());
        productService.addNewProduct(product);

        Printer.printMsg("Your product successfully add:\n" + product);
    }

    private static void updateProduct() {
        Product product = new Product();
        System.out.println("product name");
        product.setProductName(input.nextLine());

        Printer.printMsg("choose your brand:");
        Brand[] brands = brandService.loadAllBrands();
        for (Brand element : brands) {
            System.out.print(element + "\n");
        }
        String brandName = input.nextLine();
        Brand brand = brandService.isExist(brandName);

        Printer.printMsg("choose your category");
        Category[] categories = categoryService.loadAllCategories();
        for (Category element : categories) {
            System.out.print(element + "\n");
        }
        String categoryName = input.nextLine();
        Category category = categoryService.isExist(categoryName);

        product.setBrandID(brand.getBrandID());
        product.setCategoryID(category.getCategoryID());

        int affected = productService.updateProduct(product);
        Printer.printMsg(affected + " rows updated");
    }

    private static void deleteProduct() {
        System.out.println("choose undesired product.");
        Product[] products = productService.loadAllProducts();
        for (Product element : products) {
            System.out.print(element + "\n");
        }

        String name = input.nextLine();

        Product product = productService.isExist(name);

        int affected = productService.deleteProduct(product);
        Printer.printMsg(affected + " rows deleted");
    }


}
