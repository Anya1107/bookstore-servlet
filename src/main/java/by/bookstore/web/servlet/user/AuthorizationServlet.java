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

@WebServlet(Constants.AUTHORIZATION_URL)
public class AuthorizationServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl(InMemoryUserRepository.getInastance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(Constants.AUTHORIZATION_PAGE_PAHT).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        boolean isValid = true;

        if (email.isBlank() || email.isEmpty()) {
            req.setAttribute("message", "email is empty");
            isValid = false;
        }

        if (isValid) {
            User byEmail = userService.findByEmail(email);
            if (byEmail != null) {
                if (byEmail.getRole().equals(Role.USER)){
                    if(byEmail.getPassword().equals(password)){
                        req.getSession().setAttribute("user", byEmail);
                        req.getSession().setAttribute("isGuest", false);
                        req.getSession().setAttribute("isUser", true);
                        req.getSession().setAttribute("isAdmin", false);
                        resp.sendRedirect("/");
                        return;
                    } else {
                        req.setAttribute("message","Wrong password");
                        getServletContext().getRequestDispatcher(Constants.AUTHORIZATION_PAGE_PAHT).forward(req, resp);
                    }
                } else if (byEmail.getRole().equals(Role.ADMIN)){
                    if(byEmail.getPassword().equals(password)){
                        req.getSession().setAttribute("user", byEmail);
                        req.getSession().setAttribute("isGuest", false);
                        req.getSession().setAttribute("isUser", false);
                        req.getSession().setAttribute("isAdmin", true);
                        resp.sendRedirect("/");
                        return;
                    }
                    req.setAttribute("message","Wrong password");
                    getServletContext().getRequestDispatcher(Constants.AUTHORIZATION_PAGE_PAHT).forward(req, resp);

                }

            } else {
                req.setAttribute("message", "User not found");
            }
        }
        getServletContext().getRequestDispatcher(Constants.AUTHORIZATION_PAGE_PAHT).forward(req, resp);
    }
}
