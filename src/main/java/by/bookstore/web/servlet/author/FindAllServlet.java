package by.bookstore.web.servlet.author;

import by.bookstore.repository.inmemory.InMemoryAuthorRepository;
import by.bookstore.service.AuthorService;
import by.bookstore.service.AuthorServiceImpl;
import by.bookstore.web.servlet.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(Constants.FIND_ALL_AUTHOR_URL)
public class FindAllServlet extends HttpServlet {
    private final AuthorService authorService=new AuthorServiceImpl(InMemoryAuthorRepository.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("message",authorService.findAll());
        getServletContext().getRequestDispatcher(Constants.FIND_ALL_AUTHOR_PAGE_PATH).forward(req,resp);
    }
}
