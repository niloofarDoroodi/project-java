package org.example.repository.implementation;

import org.example.entity.User;
import org.example.repository.UserRepo;

import java.sql.*;

public class UserRepoImpl implements UserRepo {
    private final Connection connection;

    public UserRepoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int signUp(User user) {
        String saveSql = """
                insert into users (name_user, user_name, password, email)
                values (?, ?, ?,?)
                """;
        int insertedRows;
        try {
            PreparedStatement saveStatement = connection.prepareStatement(saveSql, Statement.RETURN_GENERATED_KEYS);

            saveStatement.setString(1, user.getName());
            saveStatement.setString(2, user.getUsername());
            saveStatement.setString(3, user.getPassword());
            saveStatement.setString(4, user.getEmail());
            insertedRows = saveStatement.executeUpdate();


            ResultSet generatedKeys = saveStatement.getGeneratedKeys();
            generatedKeys.next();
            long id = generatedKeys.getLong(1);
            user.setUserID(id);

            saveStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return insertedRows;
    }

    @Override
    public User signIn(String username, String password) {
        User user = new User();
        final String QUERY = """
                select * from users
                where user_name = ? and password = ?
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                user.setUserID(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setUsername(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
                user.setEmail(resultSet.getString(5));
            }
            preparedStatement.close();
            resultSet.close();

            return user;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User isExist(String username) {
        String findByUsernameSql = """
                select * from users
                where user_name = ?
                """;

        try {
            PreparedStatement findByUsernameStatement = connection.prepareStatement(findByUsernameSql);
            findByUsernameStatement.setString(1, username);
            ResultSet result = findByUsernameStatement.executeQuery();


            if (result.next()) {
                long id = result.getLong(1);
                String name = result.getString(2);
                String userName = result.getString(3);
                String password = result.getString(4);
                String email = result.getString(5);

                findByUsernameStatement.close();
                return new User(id,name,userName,password,email);

            } else {
                findByUsernameStatement.close();
                return null;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
