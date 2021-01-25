package by.bookstore.repository.inmemory;

import by.bookstore.entity.Book;
import by.bookstore.entity.Store;
import by.bookstore.repository.StoreRepository;
import by.bookstore.repository.DB;

import java.util.List;


public class InMemoryStoreRepository implements StoreRepository {
    private DB db = new InMemoryDB();
    private List<Store> stores;

    {
        stores=db.read(Store.class);
    }

    private static StoreRepository storeRepository;

    private InMemoryStoreRepository() {}

    public static StoreRepository getInstance(){
        if(storeRepository==null){
            storeRepository=new InMemoryStoreRepository();
            return storeRepository;
        } else{
            return storeRepository;
        }
    }

    @Override
    public void add(Store store) {
        int lastStoreId = db.getLastId(Store.class);
        store.setId(++lastStoreId);
        db.setId(lastStoreId,Store.class);
        stores.add(store);
        db.write(stores,Store.class);
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i < stores.size(); i++) {
            if(stores.get(i).getId()==id){
                stores.remove(stores.get(i));
            }
        }
        db.write(stores,Store.class);
    }

    @Override
    public Store findById(int id) {
        for (Store store : stores) {
            if (store == null) break;
            if (store.getId() == id) {
                return store;
            }
        }
        return null;
    }

    @Override
    public Store findByName(String name) {
        for (Store store : stores) {
            if (store == null) break;
            if (store.getName().equals(name)) {
                return store;
            }
        }
        return null;
    }

    @Override
    public Store[] findAll() {
        return stores.toArray(new Store[0]);
    }

    @Override
    public void addBook(Book book, String storeName) {

    }

    @Override
    public Book findBookInStore(String title, String storeName) {

        return null;
    }

    @Override
    public Book[] findAllBooksInStore(Store store) {

        return null;
    }

    @Override
    public void deleteBookInStore(String title, String storeName) {

    }
}
