package by.bookstore.service;

import by.bookstore.entity.Author;
import by.bookstore.repository.AuthorRepository;

public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void add(Author author) {
        authorRepository.add(author);
    }

    @Override
    public void delete(int id) {
       authorRepository.delete(id);
    }

    @Override
    public Author[] findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findByName(String name) {
        return authorRepository.findByName(name);
    }

    @Override
    public Author findById(int id) {
        return authorRepository.findById(id);
    }
}
