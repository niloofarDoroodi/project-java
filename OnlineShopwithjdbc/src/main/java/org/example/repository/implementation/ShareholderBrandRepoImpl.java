package org.example.repository.implementation;

import org.example.entity.Brand;
import org.example.entity.Shareholder;
import org.example.entity.ShareholderBrand;
import org.example.repository.ShareholderBrandRepo;

import java.sql.*;

public class ShareholderBrandRepoImpl implements ShareholderBrandRepo {
    private final Connection connection;

    public ShareholderBrandRepoImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public int addRelation(Shareholder shareholder, Brand brand) {
        String addQuery = """
                insert into shareholder_brand (brand_id, shareholder_id)
                values (?, ?)
                """;

        int rowsInserted;
        try {
            PreparedStatement statement = connection.prepareStatement(addQuery);
            statement.setLong(1, brand.getBrandID());
            statement.setLong(2, shareholder.getShareholderID());

            rowsInserted = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rowsInserted;
    }

    @Override
    public ShareholderBrand[] loadByShareholder(Shareholder shareholder) {
        int shareholderCount = 0;
        String findByShareholder = """
                select b.brand_id, b.brand_name, b.website, b.description
                from brand b
                join shareholder_brand sb on b.brand_id = sb.brand_id
                join shareholder s on s.shareholder_id = sb.shareholder_id
                where s.shareholder_id = ?
                """;
        String recordsByShareholder = """
                select count(shareholder_id) from shareholder_brand where shareholder_id = ?
                """;
        try {

            PreparedStatement counterStatement = connection.prepareStatement(recordsByShareholder);
            counterStatement.setLong(1, shareholder.getShareholderID());
            ResultSet result = counterStatement.executeQuery();
            while (result.next()) {
                shareholderCount = result.getInt("count");
            }

            ShareholderBrand[] shareholders = new ShareholderBrand[shareholderCount];
            PreparedStatement findByShareholderStmt = connection.prepareStatement(findByShareholder);
            findByShareholderStmt.setLong(1, shareholder.getShareholderID());
            ResultSet resultSet = findByShareholderStmt.executeQuery();

            int counter = 0;
            while (resultSet.next()) {
                Brand brand = new Brand(resultSet.getLong("brand_id"),
                        resultSet.getString("brand_name"),
                        resultSet.getString("website"),
                        resultSet.getString("description"));

                shareholders[counter].setShareholder(shareholder);
                shareholders[counter].setBrand(brand);
                counter++;
            }
            return shareholders;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ShareholderBrand[] loadByBrand(Brand brand) {
        int brandCount = 0;
        String findByBrand = """
                select s.shareholder_id, s.shareholder_name, s.phone_number, s.national_code
                from shareholder s
                join shareholder_brand sb on s.shareholder_id = sb.shareholder_id
                join brand b on b.brand_id = sb.brand_id
                where b.brand_id = ?;
                """;
        String recordsByBrand = """
                select count(brand_id) from shareholder_brand where brand_id = ?
                """;
        try {

            PreparedStatement counterStatement = connection.prepareStatement(recordsByBrand);
            counterStatement.setLong(1, brand.getBrandID());
            ResultSet result = counterStatement.executeQuery();
            while (result.next()) {
                brandCount = result.getInt("count");
            }

            ShareholderBrand[] shareholders = new ShareholderBrand[brandCount];
            PreparedStatement findByBrandStmt = connection.prepareStatement(findByBrand);
            findByBrandStmt.setLong(1, brand.getBrandID());
            ResultSet resultSet = findByBrandStmt.executeQuery();

            int counter = 0;
            while (resultSet.next()) {
                Shareholder shareholder = new Shareholder(resultSet.getLong("shareholder_id"),
                        resultSet.getString("shareholder_name"),
                        resultSet.getLong("phone_number"),
                        resultSet.getLong("national_code"));

                shareholders[counter].setShareholder(shareholder);
                shareholders[counter].setBrand(brand);
                counter++;
            }
            return shareholders;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ShareholderBrand[] loadAll() {
        ShareholderBrand[] shareholderBrands = new ShareholderBrand[recordCounter()];

        String allQuery = """
                SELECT s.shareholder_id, s.shareholder_name, s.phone_number, s.national_code, b.brand_id, b.brand_name, b.website, b.description
                FROM shareholder s
                JOIN shareholder_brand sb ON s.shareholder_id = sb.shareholder_id
                JOIN brand b ON b.brand_id = sb.brand_id;
                """;

        try {
            PreparedStatement statement = connection.prepareStatement(allQuery);
            ResultSet resultSet = statement.executeQuery();

            int count = 0;
            while (resultSet.next()) {

                Shareholder shareholder = new Shareholder(resultSet.getLong("shareholder_id"),
                        resultSet.getString("shareholder_name"),
                        resultSet.getLong("phone_number"),
                        resultSet.getLong("national_code"));

                Brand brand = new Brand(resultSet.getLong("brand_id"),
                        resultSet.getString("brand_name"),
                        resultSet.getString("website"),
                        resultSet.getString("description"));

                shareholderBrands[count].setShareholder(shareholder);
                shareholderBrands[count].setBrand(brand);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return shareholderBrands;
    }

    public int recordCounter() {
        int count;
        try {
            Statement statement = connection.createStatement();
            String query = "select count(*) from shareholder_brand";
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            count = resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

}
