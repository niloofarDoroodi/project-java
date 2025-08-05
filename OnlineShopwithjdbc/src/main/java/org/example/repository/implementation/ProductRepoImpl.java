package org.example.repository.implementation;

import org.example.entity.Category;
import org.example.entity.Product;
import org.example.repository.ProductRepo;

import java.sql.*;

public class ProductRepoImpl implements ProductRepo {
    private final Connection connection;

    public ProductRepoImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void addNewProduct(Product product) {
        String addProductSql = """
                insert into product (product_name, created_date, category_id, brand_id)
                values (?, ?, ?, ?)""";

        try {
            PreparedStatement addProductStatement = connection.prepareStatement(addProductSql, Statement.RETURN_GENERATED_KEYS);
            addProductStatement.setString(1, product.getProductName());
            addProductStatement.setDate(2, new Date(product.getCreatedDate().getTime()));
            addProductStatement.setLong(3, product.getCategoryID());
            addProductStatement.setLong(4, product.getBrandID());
            addProductStatement.executeUpdate();

            ResultSet generatedId = addProductStatement.getGeneratedKeys();
            generatedId.next();
            product.setProductID(generatedId.getLong(1));

            addProductStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int updateProduct(Product product) {
        String updateProductQuery = """
                update product set product_name = ?, category_id = ?, brand_id = ?
                where product_id = ?
                """;
        int changedRows;
        try {
            PreparedStatement updateProductStatement = connection.prepareStatement(updateProductQuery);
            updateProductStatement.setString(1, product.getProductName());
            updateProductStatement.setLong(2, product.getCategoryID());
            updateProductStatement.setLong(3, product.getBrandID());
            updateProductStatement.setLong(4, product.getProductID());
            changedRows = updateProductStatement.executeUpdate();
            updateProductStatement.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return changedRows;
    }

    @Override
    public int deleteProduct(Product product) {
        String deleteSql = """
                delete from product
                where product_id = ?
                """;

        int affectedRows;
        try {
            PreparedStatement deleteProductStatement = connection.prepareStatement(deleteSql);
            deleteProductStatement.setLong(1, product.getProductID());
            affectedRows = deleteProductStatement.executeUpdate();
            deleteProductStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return affectedRows;
    }

    @Override
    public Product isExist(String productName) {
        String findByProductNameSql = """
                select * from product
                where product_name = ?
                """;

        try {
            PreparedStatement findByProductNameStatement = connection.prepareStatement(findByProductNameSql);
            findByProductNameStatement.setString(1, productName);
            ResultSet result = findByProductNameStatement.executeQuery();


            if (result.next()) {
                long id = result.getLong(1);
                String name = result.getString(2);
                Date date = result.getDate(3);
                long catId = result.getLong(4);
                long brandId = result.getLong(5);

                findByProductNameStatement.close();
                return new Product(id, name, date, catId, brandId);

            } else {
                findByProductNameStatement.close();
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product findByBrand(Long brandID) {
        String findByBrandSql = """
                select * from product
                where brand_id = ?
                """;
        try {
            PreparedStatement brandIDStatement = connection.prepareStatement(findByBrandSql);
            brandIDStatement.setLong(1, brandID);
            ResultSet result = brandIDStatement.executeQuery();


            if (result.next()) {
                long id = result.getLong(1);
                String name = result.getString(2);
                Date date = result.getDate(3);
                long catId = result.getLong(4);
                long brandId = result.getLong(5);

                brandIDStatement.close();
                return new Product(id, name, date, catId, brandId);

            } else {
                brandIDStatement.close();
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product findByCategory(Long categoryID) {
        String findByCategorySql = """
                select * from product
                where category_id = ?
                """;
        try {
            PreparedStatement categoryIDStatement = connection.prepareStatement(findByCategorySql);
            categoryIDStatement.setLong(1, categoryID);
            ResultSet result = categoryIDStatement.executeQuery();


            if (result.next()) {
                long id = result.getLong(1);
                String name = result.getString(2);
                Date date = result.getDate(3);
                long catId = result.getLong(4);
                long brandId = result.getLong(5);

                categoryIDStatement.close();
                return new Product(id, name, date, catId, brandId);

            } else {
                categoryIDStatement.close();
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product[] loadAllProducts() {
        Product[] products = new Product[recordCounter()];
        try {
            final String QUERY = "SELECT * FROM product";
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            ResultSet rs = preparedStatement.executeQuery();

            int counter = 0;
            while (rs.next()) {
                products[counter].setProductID(rs.getLong(1));
                products[counter].setProductName(rs.getString(2));
                products[counter].setCreatedDate(rs.getDate(3));
                products[counter].setCategoryID(rs.getLong(4));
                products[counter].setBrandID(rs.getLong(5));

                counter++;
            }
            preparedStatement.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public int recordCounter() {
        int count;
        try {
            Statement statement = connection.createStatement();
            String query = "select count(*) from product";
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            count = resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }
}
