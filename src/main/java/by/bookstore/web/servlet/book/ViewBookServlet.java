package by.bookstore.web.servlet.book;

import by.bookstore.entity.Book;
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

@WebServlet(Constants.VIEW_BOOK_URL)
public class ViewBookServlet extends HttpServlet {
    private final BookService bookService=new BookServiceImpl(InMemoryBookRepository.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getParameter("id");
        int id=Integer.parseInt(bookId);
        Book byId = bookService.findById(id);
        req.setAttribute("book", byId);
        getServletContext().getRequestDispatcher(Constants.VIEW_BOOK_PAGE_PATH).forward(req,resp);
    }
}
