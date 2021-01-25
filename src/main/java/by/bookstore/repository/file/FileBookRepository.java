package by.bookstore.repository.file;

import by.bookstore.entity.Author;
import by.bookstore.entity.Book;
import by.bookstore.entity.Category;
import by.bookstore.repository.BookRepository;
import by.bookstore.repository.DB;

import java.util.ArrayList;
import java.util.List;

public class FileBookRepository implements BookRepository {
    private DB db = new FileDB();
    private List<Book> temp;


    {
       temp=db.read(Book.class);
    }

    public List<Book> getBookTemp() {
        return temp;
    }

    @Override
    public void add(Book book) {
        int lastBookId = db.getLastId(Book.class);
        book.setId(++lastBookId);
        db.setId(lastBookId,Book.class);
        temp.add(book);
        db.write(temp,Book.class);
    }

    @Override
    public void delete(int id) {
        for(Book book:temp){
            if(book==null) break;
            if(book.getId()==id){
                temp.remove(book);
            }
        }
        db.write(temp,Book.class);
    }

    @Override
    public Book findByTitle(String title) {
        for(Book book:temp){
            if(book.getTitle().equals(title)){
                return book;
            }
        }
        return null;
    }

    @Override
    public Book findById(int id) {
        for(Book book:temp){
            if(book.getId()==id){
                return book;
            }
        }
        return null;
    }

    @Override
    public Book[] findByCategory(Category category) {
        Book []books=new Book[20];
        for (int i = 0; i < books.length; i++) {
            for (Book book : books) {
                if(book==null) break;
                if(book.getCategory().equals(category)){
                    books[i]=book;
                }
            }
        }
        return books;
    }


    @Override
    public Book[] findAllByDescription(String description) {
        Book []books=new Book[20];
        for (int i = 0; i < books.length; i++) {
            for (Book book : books) {
                if(book==null) break;
                if(book.getDescription().equals(description)){
                    books[i]=book;
                }
            }
        }
        return books;
    }

    @Override
    public Book[] findAll() {
        return temp.toArray(new Book[0]);
    }

    @Override
    public Book[] findByAuthorName(Author author) {
        Book []books=new Book[20];
        for (int i = 0; i < books.length; i++) {
            for(Book book:temp){
                if(book==null) break;;
                if(book.getAuthor().getName().equals(author.getName())){
                    books[i]=book;
                }
            }
        }
        return books;
    }

    @Override
    public Book[] findByPrice(int price) {
        List<Book > books=new ArrayList<>();
        for (Book book : books) {
            if(book.getPrice()==price){
                books.add(book);
            }
        }
        return books.toArray(new Book[0]);
    }
}
