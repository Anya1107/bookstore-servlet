package by.bookstore.service;

import by.bookstore.entity.Author;
import by.bookstore.entity.Book;
import by.bookstore.entity.Category;
import by.bookstore.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;


public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void add(Book book) {
        bookRepository.add(book);
    }

    @Override
    public void delete(int id) {
        bookRepository.delete(id);
    }

    @Override
    public Book[] findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public Book findById(int id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book[] findByCategory(Category category) {
        return bookRepository.findByCategory(category);
    }


    @Override
    public Book[] findByAuthorName(Author author) {
        return bookRepository.findByAuthorName(author);
    }


    @Override
    public List<Book> search(String query) {
        Book byTitle = bookRepository.findByTitle(query);
        Book[] allByDescription = bookRepository.findAllByDescription(query);
        Book[] byAuthorName = bookRepository.findByAuthorName(new Author(query));
        boolean matches = query.matches("[\\d]+");
        List<Book> books = new ArrayList<>();
        if (matches) {
            int price = Integer.parseInt(query);
            Book[] byPrice = bookRepository.findByPrice(price);
            books.addAll(List.of(byPrice));
        }
        if (byTitle != null) {
            books.add(byTitle);
        }
        books.addAll(List.of(allByDescription));
        books.addAll(List.of(byAuthorName));
        return books;
    }
}
