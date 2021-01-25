package by.bookstore.service;

import by.bookstore.entity.Basket;
import by.bookstore.entity.Book;
import by.bookstore.entity.Store;

public interface BasketService {
    void addBook(Basket basket, Book book, Store store);
}
