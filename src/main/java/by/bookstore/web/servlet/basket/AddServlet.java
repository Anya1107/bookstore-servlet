package by.bookstore.web.servlet.basket;

import by.bookstore.entity.Basket;
import by.bookstore.entity.Book;
import by.bookstore.repository.inmemory.InMemoryBookRepository;
import by.bookstore.service.BasketService;
import by.bookstore.service.BookService;
import by.bookstore.service.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/basket/add")
public class AddServlet extends HttpServlet {

    private BookService bookService = new BookServiceImpl(InMemoryBookRepository.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Basket basket = (Basket) req.getSession().getAttribute("basket");
        Book byId = bookService.findById(Integer.parseInt(id));
        basket.addBook(byId);
        resp.sendRedirect("/");
    }
}
