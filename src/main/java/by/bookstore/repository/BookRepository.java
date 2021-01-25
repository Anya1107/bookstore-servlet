package by.bookstore.repository;


import by.bookstore.entity.Author;
import by.bookstore.entity.Book;
import by.bookstore.entity.Category;

public interface BookRepository {
    void add(Book book);
    void delete(int id);
    Book findByTitle(String title);
    Book findById(int id);
    Book [] findByCategory(Category category);
    Book[] findAllByDescription(String description);
    Book[] findAll();
    Book[] findByAuthorName(Author author);
    Book[] findByPrice(int price);
}
