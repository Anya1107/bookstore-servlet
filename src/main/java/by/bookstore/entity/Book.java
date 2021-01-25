package by.bookstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {
    private int id;
    private String title;
    private Author author;
    private int price;
    private String description;
    private Category category;


    public Book(String title) {
        this.title = title;
    }


    public Book(String title, Author author, int price, String description) {
        this.title = title;
        this.price = price;
        this.author=author;
        this.description=description;
    }

    public Book(String title, Author author, int price, String description, Category category) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.description = description;
        this.category = category;
    }
}
