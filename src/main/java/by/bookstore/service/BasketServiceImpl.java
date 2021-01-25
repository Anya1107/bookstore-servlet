package by.bookstore.service;

import by.bookstore.entity.Basket;
import by.bookstore.entity.Book;
import by.bookstore.entity.Store;
import by.bookstore.repository.BasketRepository;

public class BasketServiceImpl implements BasketService{
    private BasketRepository basketRepository;

    public BasketServiceImpl(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Override
    public void addBook(Basket basket, Book book, Store store) {
        basketRepository.addBook(basket,book,store);
    }
}
