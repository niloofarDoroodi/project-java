package org.example.repository.implementation;

import org.example.entity.Category;
import org.example.repository.CategoryRepo;

import java.sql.*;

public class CategoryRepoImpl implements CategoryRepo {
    private final Connection connection;

    public CategoryRepoImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public int addNewCategory(Category category) {
        String addCategorySql = """
                insert into category (category_name, description)
                values (?, ?)""";

        int insertedRows;
        try {
            PreparedStatement addCategoryStatement = connection.prepareStatement(addCategorySql, Statement.RETURN_GENERATED_KEYS);
            addCategoryStatement.setString(1, category.getCategoryName());
            addCategoryStatement.setString(2, category.getDescription());
            insertedRows = addCategoryStatement.executeUpdate();

            ResultSet generatedId = addCategoryStatement.getGeneratedKeys();
            generatedId.next();
            category.setCategoryID(generatedId.getLong(1));

            addCategoryStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return insertedRows;
    }

    @Override
    public int updateCategory(Category category) {
        String updateCategory = """ 
                update category set category_name = ?, description = ?
                where category_id = ?
                """;

        int changedRows;
        try {
            PreparedStatement updateCategoryStatement = connection.prepareStatement(updateCategory);
            updateCategoryStatement.setString(1, category.getCategoryName());
            updateCategoryStatement.setString(2, category.getDescription());
            updateCategoryStatement.setLong(3, category.getCategoryID());
            changedRows = updateCategoryStatement.executeUpdate();
            updateCategoryStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return changedRows;
    }

    @Override
    public int deleteCategory(Category category) {
        String deleteSql = """
                delete from category
                where category_id = ?
                """;

        int affectedRows;
        try {
            PreparedStatement deleteCategoryStatement = connection.prepareStatement(deleteSql);
            deleteCategoryStatement.setLong(1, category.getCategoryID());
            affectedRows = deleteCategoryStatement.executeUpdate();
            deleteCategoryStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return affectedRows;
    }

    @Override
    public Category isExist(String categoryName) {
        String findByCategoryNameSql = """
                select * from category
                where category_name = ?
                """;

        try {
            PreparedStatement findByCategoryNameStatement = connection.prepareStatement(findByCategoryNameSql);
            findByCategoryNameStatement.setString(1, categoryName);
            ResultSet result = findByCategoryNameStatement.executeQuery();


            if (result.next()) {
                long id = result.getLong(1);
                String name = result.getString(2);
                String description = result.getString(3);

                findByCategoryNameStatement.close();
                return new Category(id,name,description);

            } else {
                findByCategoryNameStatement.close();
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category[] loadAllCategories() {
        Category[] categories = new Category[recordCounter()];
        try {
            final String QUERY = "SELECT * FROM category";
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            ResultSet rs = preparedStatement.executeQuery();

            int counter = 0;
            while (rs.next()) {
                categories[counter].setCategoryID(rs.getLong(1));
                categories[counter].setCategoryName(rs.getString(2));
                categories[counter].setDescription(rs.getString(3));
                counter++;
            }
            preparedStatement.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
    @Override
    public int recordCounter() {
        int count;
        try {
            Statement statement = connection.createStatement();
            String query = "select count(*) from category";
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            count = resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

}
