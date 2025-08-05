package org.example.service;

import org.example.entity.Category;

public interface CategoryService {
    int addNewCategory(Category category);

    int updateCategory(Category category);

    int deleteCategory(Category category);

    Category[] loadAllCategories();

    int recordCounter();

    Category isExist(String categoryName);

}
