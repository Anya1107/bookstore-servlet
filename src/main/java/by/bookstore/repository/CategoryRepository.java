package by.bookstore.repository;

import by.bookstore.entity.Category;

public interface CategoryRepository {
    void add(Category category);
    void delete(int id);
    Category findById(int id);
    Category[] findAll();
}
