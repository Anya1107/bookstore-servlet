package by.bookstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author implements Serializable {
    private String name;
    private int id;

    public Author(String name) {
        this.name = name;
    }
}
