package by.bookstore.web.servlet.user;

import by.bookstore.entity.Store;
import by.bookstore.entity.User;
import by.bookstore.repository.inmemory.InMemoryUserRepository;
import by.bookstore.service.UserService;
import by.bookstore.service.UserServiceImpl;
import by.bookstore.web.servlet.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(Constants.FIND_BY_EMAIL_USER_URL)
public class FindByEmailServlet extends HttpServlet {
    private final UserService userService=new UserServiceImpl(InMemoryUserRepository.getInastance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(Constants.FIND_BY_EMAIL_USER_PAHE_PATH).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");

        if (valid(email,req)) {
            find(email,req,resp);
        } else {
            getServletContext().getRequestDispatcher(Constants.FIND_BY_EMAIL_USER_PAHE_PATH).forward(req,resp);
        }
    }

    private boolean valid(String email,HttpServletRequest req){
        if (email.isEmpty() || email.isBlank()) {
            req.setAttribute("message", "email is empty");
            return false;
        }
        return true;
    }

    private void find(String email,HttpServletRequest req,HttpServletResponse resp) throws IOException,ServletException{
        User byEmail = userService.findByEmail(email);
        if (byEmail == null) {
            req.setAttribute("email", email);
            req.setAttribute("message", "User not found");
        } else {
            req.setAttribute("user", byEmail);
        }
        getServletContext().getRequestDispatcher(Constants.FIND_BY_EMAIL_USER_PAHE_PATH).forward(req,resp);
    }
}
