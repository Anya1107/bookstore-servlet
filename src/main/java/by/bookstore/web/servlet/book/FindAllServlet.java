package by.bookstore.web.servlet.book;

import by.bookstore.repository.inmemory.InMemoryBookRepository;
import by.bookstore.service.BookService;
import by.bookstore.service.BookServiceImpl;
import by.bookstore.web.servlet.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(Constants.FIND_ALL_BOOK_URL)
public class FindAllServlet extends HttpServlet {
    private final BookService bookService=new BookServiceImpl(InMemoryBookRepository.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("message",bookService.findAll());
        getServletContext().getRequestDispatcher(Constants.FIND_ALL_BOOK_PAGE_PATH).forward(req,resp);
    }
}
