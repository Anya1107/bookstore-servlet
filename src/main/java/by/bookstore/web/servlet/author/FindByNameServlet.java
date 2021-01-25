package by.bookstore.web.servlet.author;

import by.bookstore.entity.Author;
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

@WebServlet(Constants.FIND_BY_NAME_AUTHOR_URL)
public class FindByNameServlet extends HttpServlet {
    private final AuthorService authorService = new AuthorServiceImpl(InMemoryAuthorRepository.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(Constants.FIND_BY_NAME_AUTHOR_PATH_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");

        if (valid(name,req)) {
           find(name,req,resp);
        } else {
            getServletContext().getRequestDispatcher(Constants.FIND_BY_NAME_AUTHOR_PATH_PAGE).forward(req, resp);
        }
    }

    private boolean valid(String name,HttpServletRequest req){
        if (name.isEmpty() || name.isBlank()) {
            req.setAttribute("message", "name is empty");
            return false;
        }
        return true;
    }

    private void find(String name,HttpServletRequest req,HttpServletResponse resp) throws IOException,ServletException{
        Author byName = authorService.findByName(name);
        if (byName == null) {
            req.setAttribute("name", name);
            req.setAttribute("message", "Author not found");
        } else {
            req.setAttribute("author", byName);
        }
        getServletContext().getRequestDispatcher(Constants.FIND_BY_NAME_AUTHOR_PATH_PAGE).forward(req, resp);
    }
}
