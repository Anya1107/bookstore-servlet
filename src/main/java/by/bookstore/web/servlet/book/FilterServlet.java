package by.bookstore.web.servlet.book;

import by.bookstore.entity.Book;
import by.bookstore.entity.Category;
import by.bookstore.repository.inmemory.InMemoryBookRepository;
import by.bookstore.service.BookService;
import by.bookstore.service.BookServiceImpl;
import by.bookstore.service.CategoryService;
import by.bookstore.service.CategoryServiceImpl;
import by.bookstore.web.servlet.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(Constants.FILTER_BOOK_URL)
public class FilterServlet extends HttpServlet {
    private BookService bookService=new BookServiceImpl(InMemoryBookRepository.getInstance());
    private CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Category byId = categoryService.findById(Integer.parseInt(id));
        Book[] byCategory = bookService.findByCategory(byId);
        req.setAttribute("books", byCategory);
        getServletContext().getRequestDispatcher(Constants.FILTER_BOOK_PAGE_PATH).forward(req,resp);
    }
}
