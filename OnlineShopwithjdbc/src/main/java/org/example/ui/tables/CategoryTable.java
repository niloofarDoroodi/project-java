package org.example.ui.tables;

import org.example.entity.Category;
import org.example.service.CategoryService;
import org.example.service.ProductService;
import org.example.ui.Printer;
import org.example.util.ApplicationContext;
import org.example.util.Constants;

import java.util.Scanner;

public class CategoryTable {
    static Scanner input = new Scanner(System.in);
    private static final CategoryService categoryService = ApplicationContext.getCategoryService();
    private static final ProductService productService = ApplicationContext.getProductService();

    public static void categoryAccess() {
        int categoryItems;
        boolean breakLoop = false;

        while (!breakLoop) {
            Printer.printMenu(Constants.CATEGORY_ITEMS);
            categoryItems = input.nextInt();

            switch (categoryItems) {
                case 1 -> {
                    addCategory();
                }
                case 2 -> {
                    updateCategory();
                }
                case 3 -> {
                    deleteCategory();
                }
                case 4 -> {
                    breakLoop = true;
                    Printer.printMsg(Constants.ALERT_EXIT);
                }

                default -> Printer.printMsg(Constants.ALERT_CHOICE);
            }
        }

    }

    private static void addCategory() {
        Category category = new Category();

        System.out.println("Enter Your Category Name: ");
        category.setCategoryName(input.nextLine());

        System.out.println("Enter Your description: ");
        category.setDescription(input.nextLine());

        int addedCategories = categoryService.addNewCategory(category);
        Printer.printMsg(String.valueOf(addedCategories));
    }

    private static void updateCategory() {
        Category category = new Category();

        System.out.println("Enter Your Category Name: ");
        category.setCategoryName(input.nextLine());

        System.out.println("Enter Your description: ");
        category.setDescription(input.nextLine());

        Printer.printMsg(categoryService.updateCategory(category) + " rows have effected.");
    }

    private static void deleteCategory() {
        Category category = new Category();

        System.out.println("Enter Your Category Name: ");
        category.setCategoryName(input.nextLine());


        System.out.println("Enter Your description: ");
        category.setDescription(input.nextLine());

        if (productService.findByCategory(category.getCategoryID()) != null) {
            System.out.println("The related product is deleted.");
            productService.deleteProduct(productService.findByCategory(category.getCategoryID()));
        }

        System.out.println(categoryService.deleteCategory(category) + " have been deleted");
    }
}
