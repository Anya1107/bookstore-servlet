package by.bookstore.repository.file;

import by.bookstore.entity.Basket;
import by.bookstore.entity.Book;
import by.bookstore.entity.Store;
import by.bookstore.repository.BasketRepository;
import by.bookstore.repository.DB;

import java.util.List;

public class FileBasketRepository implements BasketRepository {
    private DB db = new FileDB();
    private List<Basket> temp;


    {
        temp=db.read(Basket.class);
    }

    public List<Basket> getBasketTemp() {
        return temp;
    }

    @Override
    public void addBook(Basket basket, Book book, Store store) {

    }
}
