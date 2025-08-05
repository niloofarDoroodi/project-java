package org.example.repository.implementation;

import org.example.entity.Shareholder;
import org.example.repository.ShareholderRepo;

import java.sql.*;

public class ShareholderRepoImpl implements ShareholderRepo {

    private final Connection connection;

    public ShareholderRepoImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public int addNewShareholder(Shareholder shareholder) {
        String addShareholderSql = """
                insert into shareholder (shareholder_name, phone_number, national_code)
                values (?, ?, ?)""";
        int added = 0;

        try {
            PreparedStatement addShareholderStatement = connection.prepareStatement(addShareholderSql, Statement.RETURN_GENERATED_KEYS);
            addShareholderStatement.setString(1, shareholder.getShareholderName());
            addShareholderStatement.setLong(2, shareholder.getPhoneNumber());
            addShareholderStatement.setLong(2, shareholder.getNationalCode());
            added = addShareholderStatement.executeUpdate();

            ResultSet generatedId = addShareholderStatement.getGeneratedKeys();
            generatedId.next();
            shareholder.setShareholderID(generatedId.getLong(1));

            addShareholderStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return added;
    }

    @Override
    public int updateShareholder(Shareholder shareholder) {
        String updateShareholder = """ 
                update shareholder set shareholder_name = ?, phone_number = ?, national_code = ?
                where shareholder_id = ?
                """;

        int changedRows;
        try {
            PreparedStatement updateShareholderStatement = connection.prepareStatement(updateShareholder);
            updateShareholderStatement.setString(1, shareholder.getShareholderName());
            updateShareholderStatement.setLong(2, shareholder.getPhoneNumber());
            updateShareholderStatement.setLong(3, shareholder.getNationalCode());
            updateShareholderStatement.setLong(4, shareholder.getShareholderID());
            changedRows = updateShareholderStatement.executeUpdate();
            updateShareholderStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return changedRows;
    }

    @Override
    public int deleteShareholder(Shareholder shareholder) {
        String deleteSql = """
                delete from shareholder
                where shareholder_id = ?
                """;

        int affectedRows;
        try {
            PreparedStatement deleteShareholderStatement = connection.prepareStatement(deleteSql);
            deleteShareholderStatement.setLong(1, shareholder.getShareholderID());
            affectedRows = deleteShareholderStatement.executeUpdate();
            deleteShareholderStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return affectedRows;
    }

    @Override
    public Shareholder isExist(String shareholderName) {
        String findByShareholderNameSql = """
                select * from shareholder
                where shareholder_name = ?
                """;

        try {
            PreparedStatement findByShareholderNameStatement = connection.prepareStatement(findByShareholderNameSql);
            findByShareholderNameStatement.setString(1, shareholderName);
            ResultSet result = findByShareholderNameStatement.executeQuery();


            if (result.next()) {
                long id = result.getLong(1);
                String name = result.getString(2);
                long phone = result.getLong(3);
                long nationalCode = result.getLong(4);

                findByShareholderNameStatement.close();
                return new Shareholder(id, name, phone, nationalCode);

            } else {
                findByShareholderNameStatement.close();
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Shareholder[] loadAllShareholders() {
        Shareholder[] shareholders = new Shareholder[recordCounter()];
        try {
            final String QUERY = "SELECT * FROM shareholder";
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            ResultSet rs = preparedStatement.executeQuery();

            int counter = 0;
            while (rs.next()) {
                shareholders[counter].setShareholderID(rs.getInt(1));
                shareholders[counter].setShareholderName(rs.getString(2));
                shareholders[counter].setPhoneNumber(rs.getLong(3));
                shareholders[counter].setNationalCode(rs.getLong(4));
                counter++;
            }
            preparedStatement.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shareholders;
    }

    public int recordCounter() {
        int count;
        try {
            Statement statement = connection.createStatement();
            String query = "select count(*) from shareholder";
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            count = resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }
}
