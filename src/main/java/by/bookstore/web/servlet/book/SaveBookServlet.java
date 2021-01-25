package by.bookstore.web.servlet.book;

import by.bookstore.entity.Author;
import by.bookstore.entity.Book;
import by.bookstore.entity.Category;
import by.bookstore.repository.inmemory.InMemoryAuthorRepository;
import by.bookstore.repository.inmemory.InMemoryBasketRepository;
import by.bookstore.repository.inmemory.InMemoryBookRepository;
import by.bookstore.service.*;
import by.bookstore.web.servlet.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(Constants.SAVE_BOOK_URL)
public class SaveBookServlet extends HttpServlet {
    private final BookService bookService=new BookServiceImpl(InMemoryBookRepository.getInstance());
    private final AuthorService authorService=new AuthorServiceImpl(InMemoryAuthorRepository.getInstance());
    private final CategoryService categoryService=new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("authors",authorService.findAll());
        req.setAttribute("categories",categoryService.findAll());
        getServletContext().getRequestDispatcher(Constants.SAVE_BOOK_PAGE_PATH).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String price1=req.getParameter("price");
        int price=0;
        int authorId=Integer.parseInt(req.getParameter("authorId"));
        Author authorById = authorService.findById(authorId);
        int categoryId=Integer.parseInt(req.getParameter("categoryId"));
        Category byId = categoryService.findById(categoryId);

        if(!price1.isEmpty()||!price1.isBlank()){
            price = Integer.parseInt(price1);
        }


        if(valid(title,description,authorId,categoryId,price1,price,req)){
            save(title, authorById, description, price, byId, resp);
        } else {
            req.setAttribute("title",title);
            req.setAttribute("description",description);
            req.setAttribute("authorId",authorById);
            req.setAttribute("categoryId",categoryId);
            req.setAttribute("price",price);
            getServletContext().getRequestDispatcher(Constants.SAVE_BOOK_PAGE_PATH).forward(req,resp);
        }
    }

    private boolean valid(String title, String description, int authorId, int categoryId, String price1, int price, HttpServletRequest req){
        if(title.isEmpty()||title.isBlank()){
            req.setAttribute("message","title is empty");
            return false;
        }

        if(description.isEmpty()||description.isBlank()){
            req.setAttribute("message","description is empty");
            return false;
        }


        if(authorId<1){
            req.setAttribute("message","negative id");
            return false;
        }

        if(categoryId<1){
            req.setAttribute("message","negative id");
            return false;
        }

        if(price1.isEmpty()||price1.isBlank()){
            req.setAttribute("message","price is empty");
            return false;
        }

        if(price<1){
            req.setAttribute("message","negative price");
            return false;
        }
        return true;
    }

    private void save(String title, Author authorById, String description, int price, Category category,HttpServletResponse resp) throws IOException{
        Book book=new Book(title,authorById,price,description,category);
        bookService.add(book);
        resp.sendRedirect("/");
    }
}
