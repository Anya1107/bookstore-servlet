package by.bookstore.web.servlet.user;

import by.bookstore.entity.Role;
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

@WebServlet(Constants.REGISTRATION_URL)
public class RegistrationServlet extends HttpServlet {
    private final UserService userService=new UserServiceImpl(InMemoryUserRepository.getInastance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(Constants.REGISTRATION_PAGE_PATH).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if(valid(firstName,lastName,email,password,req)){
            save(firstName,lastName,email,password,resp);
        } else {
            req.setAttribute("firstName",firstName);
            req.setAttribute("lastName",lastName);
            req.setAttribute("email",email);
            req.setAttribute("password",password);
            getServletContext().getRequestDispatcher(Constants.SAVE_USER_PAGE_PATH).forward(req,resp);
        }
    }

    private boolean valid(String firstName, String lastName, String email, String password, HttpServletRequest req) {
        if(firstName.isEmpty()||firstName.isBlank()){
            req.setAttribute("message","first name is empty");
            return false;
        }

        if(lastName.isEmpty()||lastName.isBlank()){
            req.setAttribute("message","last name is empty");
            return false;
        }

        if(email.isEmpty()||email.isBlank()){
            req.setAttribute("message","email is empty");
            return false;
        }

        if(password.isEmpty()||password.isBlank()){
            req.setAttribute("message","password is empty");
            return false;
        }
        return true;
    }

    private void save(String firstName, String lastName, String email, String password, HttpServletResponse resp) throws IOException{
        User user=new User(firstName,lastName,email,password, Role.USER);
        userService.add(user);
        resp.sendRedirect("/");
    }
}
