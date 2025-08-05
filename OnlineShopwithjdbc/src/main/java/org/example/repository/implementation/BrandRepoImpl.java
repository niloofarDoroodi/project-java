package org.example.repository.implementation;

import org.example.entity.Brand;
import org.example.repository.BrandRepo;

import java.sql.*;

public class BrandRepoImpl implements BrandRepo {
    private final Connection connection;

    public BrandRepoImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void addNewBrand(Brand brand) {
        String addBrandSql = """
                insert into brand (brand_name, website, description)
                values (?, ?, ?)""";

        try {
            PreparedStatement addBrandStatement = connection.prepareStatement(addBrandSql, Statement.RETURN_GENERATED_KEYS);
            addBrandStatement.setString(1, brand.getBrandName());
            addBrandStatement.setString(2, brand.getWebsite());
            addBrandStatement.setString(3, brand.getDescription());
            addBrandStatement.executeUpdate();

            ResultSet generatedId = addBrandStatement.getGeneratedKeys();
            generatedId.next();
            brand.setBrandID(generatedId.getLong(1));

            addBrandStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateBrand(Brand brand) {
        String updateBrand = """
                update brand set brand_name = ?, website = ?, description = ?
                where brand_id = ?
                """;

        int changedRows;
        try {
            PreparedStatement updateBrandStatement = connection.prepareStatement(updateBrand);
            updateBrandStatement.setString(1, brand.getBrandName());
            updateBrandStatement.setString(2, brand.getWebsite());
            updateBrandStatement.setString(3, brand.getDescription());
            updateBrandStatement.setLong(4, brand.getBrandID());
            changedRows = updateBrandStatement.executeUpdate();
            updateBrandStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return changedRows;
    }

    @Override
    public int deleteBrand(Brand brand) {
        String deleteSql = """
                delete from brand
                where brand_id = ?
                """;

        int affectedRows;
        try {
            PreparedStatement deleteBrandStatement = connection.prepareStatement(deleteSql);
            deleteBrandStatement.setLong(1, brand.getBrandID());
            affectedRows = deleteBrandStatement.executeUpdate();
            deleteBrandStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return affectedRows;
    }

    @Override
    public Brand isExist(String brandName) {
        String findByBrandNameSql = """
                select * from brand
                where brand_name = ?
                """;

        try {
            PreparedStatement findByBrandNameStatement = connection.prepareStatement(findByBrandNameSql);
            findByBrandNameStatement.setString(1, brandName);
            ResultSet result = findByBrandNameStatement.executeQuery();


            if (result.next()) {
                long id = result.getLong(1);
                String name = result.getString(2);
                String website = result.getString(3);
                String description = result.getString(4);

                findByBrandNameStatement.close();
                return new Brand(id, name, website, description);

            } else {
                findByBrandNameStatement.close();
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Brand[] loadAllBrands() {
        Brand[] brands = new Brand[recordCounter()];
        try {
            final String QUERY = "SELECT * FROM brand";
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            ResultSet rs = preparedStatement.executeQuery();

            int counter = 0;
            while (rs.next()) {
                brands[counter].setBrandID(rs.getLong(1));
                brands[counter].setBrandName(rs.getString(2));
                brands[counter].setWebsite(rs.getString(3));
                brands[counter].setDescription(rs.getString(4));
                counter++;
            }
            preparedStatement.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brands;
    }
    @Override
    public int recordCounter() {
        int count;
        try {
            Statement statement = connection.createStatement();
            String query = "select count(*) from brand";
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            count = resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }
}
