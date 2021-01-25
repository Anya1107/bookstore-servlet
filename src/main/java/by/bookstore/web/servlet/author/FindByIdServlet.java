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

@WebServlet(Constants.FIND_BY_ID_AUTHOR_URL)
public class FindByIdServlet extends HttpServlet {
    private final AuthorService authorService = new AuthorServiceImpl(InMemoryAuthorRepository.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(Constants.FIND_BY_ID_AUTHOR_PAGE_PATH).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id1 = req.getParameter("id");
        int id=0;

        if(!id1.isEmpty()||!id1.isBlank()){
            id=Integer.parseInt(id1);
        }

        if (valid(id1,id,req)) {
            find(id,req,resp);
        } else {
            getServletContext().getRequestDispatcher(Constants.FIND_BY_ID_AUTHOR_PAGE_PATH).forward(req, resp);
        }

    }

    private boolean valid(String id1,int id, HttpServletRequest req){
        if (id1.isEmpty()||id1.isBlank()) {
            req.setAttribute("message", "Id is empty");
            return false;
        } else {
            if (id < 1) {
                req.setAttribute("message", "negative id");
                return false;
            }
        }
        return true;
    }

    private void find(int id,HttpServletRequest req,HttpServletResponse resp) throws IOException,ServletException{
        Author byId = authorService.findById(id);
        if (byId == null) {
            req.setAttribute("id", id);
            req.setAttribute("message", "Author not found");
        } else {
            req.setAttribute("author", byId);
        }
        getServletContext().getRequestDispatcher(Constants.FIND_BY_ID_AUTHOR_PAGE_PATH).forward(req, resp);
    }
}
