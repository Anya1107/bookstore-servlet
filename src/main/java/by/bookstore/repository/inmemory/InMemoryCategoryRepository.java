package by.bookstore.repository.inmemory;

import by.bookstore.entity.Category;
import by.bookstore.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCategoryRepository implements CategoryRepository {
    private static List<Category> categories=new ArrayList<>();

    @Override
    public void add(Category category) {
        categories.add(category);
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i < categories.size(); i++) {
            if(categories.get(i).getId()==id){
                categories.remove(categories.get(i));
            }
        }
    }

    @Override
    public Category findById(int id) {
        for (Category category : categories) {
            if(category.getId()==id){
                return category;
            }
        }
        return null;
    }

    @Override
    public Category[] findAll() {
        return categories.toArray(new Category[0]);
    }
}
