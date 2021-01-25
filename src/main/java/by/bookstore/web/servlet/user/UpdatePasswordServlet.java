package by.bookstore.web.servlet.user;

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

@WebServlet(Constants.UPDATE_PASSWORD_USER_URL)
public class UpdatePasswordServlet extends HttpServlet {
    private UserService userService=new UserServiceImpl(InMemoryUserRepository.getInastance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users",userService.findAll());
        getServletContext().getRequestDispatcher(Constants.UPDATE_PASSWORD_USER_PAGE_PATH).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId=Integer.parseInt(req.getParameter("userId"));
        String password = req.getParameter("newPassword");

        if(valid(password,req)){
            update(password,userId,resp);
        }
    }

    private boolean valid(String password, HttpServletRequest req){
        if(password.isEmpty()||password.isBlank()){
            req.setAttribute("message","New password is empty");
            return false;
        }
        return true;
    }

    private void update(String password, int userId, HttpServletResponse resp) throws IOException{
        userService.updatePassword(password,userId);
        resp.sendRedirect("/");
    }
}
