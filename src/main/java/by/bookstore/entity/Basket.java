package by.bookstore.entity;

import java.io.Serializable;
import java.util.Arrays;

public class Basket implements Serializable {
    private int id;
    private Book []books;

    public Basket() {
    }

    public void addBook(Book book){
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null){
                books[i] = book;
                break;
            }
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    public Basket(Book[] books) {
        this.books = books;
    }

    public Book[] getBooks() {
        return books;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "id=" + id +
                ", books=" + Arrays.toString(books) +
                '}';
    }
}
