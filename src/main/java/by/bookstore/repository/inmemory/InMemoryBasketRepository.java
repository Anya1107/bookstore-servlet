package by.bookstore.repository.inmemory;

import by.bookstore.entity.Basket;
import by.bookstore.entity.Book;
import by.bookstore.entity.Store;
import by.bookstore.repository.BasketRepository;
import by.bookstore.repository.BookRepository;
import by.bookstore.repository.DB;

import java.util.List;


public class InMemoryBasketRepository implements BasketRepository {
    private DB db = new InMemoryDB();
    private static List<Basket> baskets;

    {
        baskets=db.read(Basket.class);
    }

    private static BasketRepository basketRepository;

    private InMemoryBasketRepository(){}

    public static BasketRepository getInstance(){
        if(basketRepository==null){
            basketRepository=new InMemoryBasketRepository();
            return basketRepository;
        } else{
            return basketRepository;
        }
    }

    @Override
    public void addBook(Basket basket, Book book, Store store) {
        int lastBasketId = db.getLastId(Basket.class);
        basket.setId(++lastBasketId);
        db.setId(lastBasketId,Basket.class);
        baskets.add(basket);
        db.write(baskets,Basket.class);
    }
}


