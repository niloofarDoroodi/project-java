package org.example.service;

import org.example.entity.Product;

public interface ProductService {
    void addNewProduct(Product product);

    int updateProduct(Product product);

    int deleteProduct(Product product);

    Product isExist(String productName);

    Product[] loadAllProducts();

    Product findByBrand(Long brandName);

    Product findByCategory(Long categoryName);
}
