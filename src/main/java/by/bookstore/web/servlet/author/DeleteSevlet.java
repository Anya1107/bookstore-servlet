package by.bookstore.web.servlet.author;

import by.bookstore.entity.Address;
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

@WebServlet(Constants.DELETE_AUTHOR_URL)
public class DeleteSevlet extends HttpServlet {
    private final AuthorService authorService=new AuthorServiceImpl(InMemoryAuthorRepository.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(Constants.DELETE_AUTHOR_PAGE_PATH).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
        int id1=0;

        if(!id.isEmpty()||!id.isBlank()){
            id1=Integer.parseInt(id);
        }

        if(valid(id, id1,req)){
            delete(id1,req,resp);
        } else{
            req.setAttribute("id",id);
            getServletContext().getRequestDispatcher(Constants.DELETE_AUTHOR_PAGE_PATH).forward(req,resp);
        }
    }

    private boolean valid(String id, int id1,HttpServletRequest req){
        if (id.isEmpty()||id.isBlank()) {
            req.setAttribute("message", "Id is empty");
            return false;
        } else {
            if (id1 < 1) {
                req.setAttribute("message", "negative id");
                return false;
            }
        }
        return true;
    }

    private void delete(int id,HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        Author byId = authorService.findById(id);
        if (byId == null) {
            req.setAttribute("id", id);
            req.setAttribute("message", "Address not found");
            getServletContext().getRequestDispatcher(Constants.DELETE_AUTHOR_PAGE_PATH).forward(req,resp);
        } else {
            authorService.delete(id);
            resp.sendRedirect("/");
        }
    }
}
