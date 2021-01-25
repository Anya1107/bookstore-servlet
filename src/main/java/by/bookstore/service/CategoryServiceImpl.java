package by.bookstore.service;

import by.bookstore.entity.Category;
import by.bookstore.repository.CategoryRepository;
import by.bookstore.repository.inmemory.InMemoryCategoryRepository;

public class CategoryServiceImpl implements CategoryService{
    private CategoryRepository categoryRepository=new InMemoryCategoryRepository();

    @Override
    public void add(Category category) {
    categoryRepository.add(category);
    }

    @Override
    public void delete(int id) {
        categoryRepository.delete(id);
    }

    @Override
    public Category findById(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category[] findAll() {
        return categoryRepository.findAll();
    }
}
