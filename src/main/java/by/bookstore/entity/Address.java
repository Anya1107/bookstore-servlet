package by.bookstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @AllArgsConstructor @NoArgsConstructor
public class Address implements Serializable {
    private int id;
    private String address;
    
    public Address(String address){
        this.address=address;
    }
}
