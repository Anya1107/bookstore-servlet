package by.bookstore.repository.inmemory;

import by.bookstore.entity.Author;
import by.bookstore.entity.Book;
import by.bookstore.entity.Category;
import by.bookstore.repository.BookRepository;
import by.bookstore.repository.DB;

import java.util.*;


public class InMemoryBookRepository implements BookRepository {
    private DB db = new InMemoryDB();
    private List<Book> books;

    {
        books=db.read(Book.class);
    }

    private static BookRepository bookRepository;

    private InMemoryBookRepository(){}

    public static BookRepository getInstance(){
        if(bookRepository==null){
            bookRepository=new InMemoryBookRepository();
            return bookRepository;
        } else{
            return bookRepository;
        }
    }

    @Override
    public void add(Book book) {
        int lastBookId = db.getLastId(Book.class);
        book.setId(++lastBookId);
        db.setId(lastBookId,Book.class);
        books.add(book);
        db.write(books,Book.class);
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i).getId()==id){
                books.remove(books.get(i));
            }
        }
        db.write(books,Book.class);
    }

    @Override
    public Book findByTitle(String title) {
        for (Book value : books) {
            if (value == null) break;
            if (value.getTitle().equals(title)) {
                return value;
            }
        }
        return null;
    }

    @Override
    public Book findById(int id) {
        for (Book value : books) {
            if (value == null) break;
            if (value.getId() == id) {
                return value;
            }
        }
        return null;
    }

    @Override
    public Book [] findByCategory(Category category) {
        List<Book> books1 = new ArrayList<>();
        for (Book book : books) {
            if(book==null) break;
            if(book.getCategory().equals(category)){
                books1.add(book);
            }
        }
        return books1.toArray(new Book[0]);
    }

    @Override
    public Book[] findAllByDescription(String description) {
        List<Book> books=new ArrayList<>();
        for (Book book : books) {
            if(book==null) break;
            if(book.getDescription().equals(description)){
               books.add(book);
            }
        }
        return books.toArray(new Book[0]);
    }

    @Override
    public Book[] findAll() {
        return books.toArray(new Book[0]);
    }

    @Override
    public Book[] findByAuthorName(Author author) {
        List<Book> book1=new ArrayList<>();

       for(Book book:books){
           if(book.getAuthor().getName().equals(author.getName())){
               book1.add(book);
           }
       }
        return book1.toArray(new Book[0]);
    }

    @Override
    public Book[] findByPrice(int price) {
        List<Book > books=new ArrayList<>();
        for (Book book : this.books) {
            if(book.getPrice()==price){
                books.add(book);
            }
        }
        return books.toArray(new Book[0]);
    }
}
