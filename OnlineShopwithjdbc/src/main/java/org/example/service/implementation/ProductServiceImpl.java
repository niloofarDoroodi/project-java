package org.example.service.implementation;

import org.example.entity.Product;
import org.example.repository.ProductRepo;
import org.example.service.ProductService;

public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public void addNewProduct(Product product) {
        if (isExist(product.getProductName()) == null){
            productRepo.addNewProduct(product);
        }
    }

    @Override
    public int updateProduct(Product product) {
        int affected = 0;
        if (isExist(product.getProductName()) == null){
            affected = productRepo.updateProduct(product);
        }
        return affected;
    }

    @Override
    public int deleteProduct(Product product) {
        int affected = 0;
        if (isExist(product.getProductName()) == null){
            affected = productRepo.deleteProduct(product);
        }
        return affected;
    }

    @Override
    public Product isExist(String productName) {
        return productRepo.isExist(productName);
    }

    @Override
    public Product[] loadAllProducts() {
        return productRepo.loadAllProducts();
    }

    @Override
    public Product findByBrand(Long brandName) {
        return productRepo.findByBrand(brandName);
    }

    @Override
    public Product findByCategory(Long categoryName) {
        return productRepo.findByCategory(categoryName);
    }
}
