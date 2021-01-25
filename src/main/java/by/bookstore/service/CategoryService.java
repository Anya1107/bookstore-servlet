package by.bookstore.service;

import by.bookstore.entity.Category;

public interface CategoryService {
    void add(Category category);
    void delete(int id);
    Category findById(int id);
    Category[] findAll();
}
