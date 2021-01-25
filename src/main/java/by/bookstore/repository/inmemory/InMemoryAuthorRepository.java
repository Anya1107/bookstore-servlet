package by.bookstore.repository.inmemory;

import by.bookstore.entity.Author;
import by.bookstore.repository.AuthorRepository;
import by.bookstore.repository.DB;

import java.util.List;


public class InMemoryAuthorRepository implements AuthorRepository {
    private DB db=new InMemoryDB();
    private List<Author> authors;

    {
        authors = db.read(Author.class);
    }

    private static AuthorRepository authorRepository;

    private InMemoryAuthorRepository(){}

    public static AuthorRepository getInstance(){
        if(authorRepository==null){
            authorRepository=new InMemoryAuthorRepository();
            return authorRepository;
        } else {
            return authorRepository;
        }
    }

    @Override
    public void add(Author author) {
        int lastAuthorId = db.getLastId(Author.class);
        author.setId(++lastAuthorId);
        db.setId(lastAuthorId,Author.class);
        authors.add(author);
        db.write(authors,Author.class);
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i < authors.size(); i++) {
            if(authors.get(i).getId()==id){
                authors.remove(authors.get(i));
            }
        }
        db.write(authors,Author.class);
    }

    @Override
    public Author findByName(String name) {
        for (Author author : authors) {
            if (author == null) break;
            if (author.getName().equals(name)) {
                return author;
            }
        }
        return null;
    }

    @Override
    public Author findById(int id) {
        for (Author author : authors) {
            if (author == null) break;
            if (author.getId() == id) {
                return author;
            }
        }
        return null;
    }


    @Override
    public Author[] findAll() {
        return authors.toArray(new Author[0]);
    }
}
