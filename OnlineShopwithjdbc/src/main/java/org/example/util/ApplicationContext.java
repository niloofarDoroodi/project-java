package org.example.util;//package org.example.util;
import org.example.connection.DBConnection;
import org.example.repository.*;
import org.example.repository.implementation.*;
import org.example.service.*;
import org.example.service.implementation.*;

import java.sql.Connection;

public class ApplicationContext {

    private static final Connection connection;

    private static final UserRepo userRepo;
    private static final UserService userService;

    private static final CategoryRepo categoryRepo;
    private static final CategoryService categoryService;

    private static final BrandRepo brandRepo;
    private static final BrandService brandService;

    private static final ProductRepo productRepo;
    private static final ProductService productService;

    private static final ShareholderRepo shareholderRepo;
    private static final ShareholderService shareholderService;

    private static final ShareholderBrandRepo shareholderBrandRepo;
    private static final ShareholderBrandService shareholderBrandService;

    static {
        connection = DBConnection.getConnection();

        userRepo = new UserRepoImpl(connection);
        userService = new UserServiceImpl(userRepo);

        categoryRepo = new CategoryRepoImpl(connection);
        categoryService = new CategoryServiceImpl(categoryRepo);

        brandRepo = new BrandRepoImpl(connection);
        brandService = new BrandServiceImpl(brandRepo);

        productRepo = new ProductRepoImpl(connection);
        productService = new ProductServiceImpl(productRepo);

        shareholderRepo = new ShareholderRepoImpl(connection);
        shareholderService = new ShareholderServiceImpl(shareholderRepo);

        shareholderBrandRepo = new ShareholderBrandRepoImpl(connection);
        shareholderBrandService = new ShareholderBrandServiceImpl(shareholderBrandRepo);
    }



    public static UserService getUserService() {
        return userService;
    }

    public static CategoryService getCategoryService() {
        return categoryService;
    }

    public static BrandService getBrandService() {
        return brandService;
    }

    public static ProductService getProductService() {
        return productService;
    }

    public static ShareholderService getShareholderService(){
        return shareholderService;
    }

    public static ShareholderBrandService getShareholderBrandService() {
        return shareholderBrandService;
    }
}
