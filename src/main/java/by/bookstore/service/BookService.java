package by.bookstore.service;

import by.bookstore.entity.Author;
import by.bookstore.entity.Book;
import by.bookstore.entity.Category;

import java.util.List;

public interface BookService {
    void add(Book book);
    void delete(int id);
    Book[] findAll();
    Book findByTitle(String title);
    Book findById(int id);
    Book [] findByCategory(Category category);
    Book[] findByAuthorName(Author author);

    List<Book> search(String query);
}
