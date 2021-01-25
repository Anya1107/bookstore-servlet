package by.bookstore.repository;

import java.util.List;

public interface DB {

    <T> void write(List<T> list, Class<T> clazz);

    <T> List<T> read(Class<T> clazz);

    int getLastId(Class<?> clazz);

    void setId(int id, Class<?> clazz);
}
