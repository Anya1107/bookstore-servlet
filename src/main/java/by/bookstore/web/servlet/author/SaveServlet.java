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

@WebServlet(Constants.SAVE_AUTHOR_URL)
public class SaveServlet extends HttpServlet {
    private final AuthorService authorService=new AuthorServiceImpl(InMemoryAuthorRepository.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(Constants.SAVE_AUTHOR_PAGE_PATH).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");

        if(valid(name,req)){
            save(name,resp);
        } else {
            req.setAttribute("name", name);
            getServletContext().getRequestDispatcher(Constants.SAVE_AUTHOR_PAGE_PATH).forward(req,resp);
        }
    }

    private boolean valid(String name,HttpServletRequest req){
        if(name.isEmpty()||name.isBlank()){
            req.setAttribute("message", "Name is empty.");
            return false;
        }
        return true;
    }

    private void save(String name,HttpServletResponse resp) throws IOException,ServletException{
        Author author=new Author(name);
        authorService.add(author);
        resp.sendRedirect("/");
    }
}
