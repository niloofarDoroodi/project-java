package org.example.service.implementation;

import org.example.entity.Category;
import org.example.repository.CategoryRepo;
import org.example.repository.UserRepo;
import org.example.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public int addNewCategory(Category category) {
        int inserted = 0;
        if (categoryRepo.isExist(category.getCategoryName()) == null) {
            inserted = categoryRepo.addNewCategory(category);
        }
        return inserted;
    }

    @Override
    public int updateCategory(Category category) {
        int affected = 0;
        if (categoryRepo.isExist(category.getCategoryName()) == null) {
            affected = categoryRepo.updateCategory(category);
        }
        return affected;
    }

    @Override
    public int deleteCategory(Category category) {
        int affected = 0;
        if (categoryRepo.isExist(category.getCategoryName()) == null) {
            affected = categoryRepo.updateCategory(category);
        }
        return affected;
    }

    @Override
    public Category[] loadAllCategories() {
        return categoryRepo.loadAllCategories();
    }

    @Override
    public int recordCounter() {
        return categoryRepo.recordCounter();
    }

    @Override
    public Category isExist(String categoryName) {
        return categoryRepo.isExist(categoryName);
    }

}
