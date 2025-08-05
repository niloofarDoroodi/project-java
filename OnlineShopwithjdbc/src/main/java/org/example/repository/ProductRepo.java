package org.example.repository;

import org.example.entity.Product;

public interface ProductRepo {

    void addNewProduct(Product product);

    int updateProduct(Product product);

    int deleteProduct(Product product);

    Product isExist(String productName);

    Product findByBrand(Long brandName);

    Product findByCategory(Long categoryName);

    Product[] loadAllProducts();
}
