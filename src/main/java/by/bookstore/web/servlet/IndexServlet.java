package by.bookstore.web.servlet;


import by.bookstore.repository.inmemory.InMemoryBookRepository;
import by.bookstore.service.BookService;
import by.bookstore.service.BookServiceImpl;
import by.bookstore.service.CategoryService;
import by.bookstore.service.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class IndexServlet extends HttpServlet {
    private final BookService bookService=new BookServiceImpl(InMemoryBookRepository.getInstance());
    private CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("books",bookService.findAll());
        req.getSession().setAttribute("categories", categoryService.findAll());
        getServletContext().getRequestDispatcher("/pages/index.jsp").forward(req,resp);
    }
}
