package by.bookstore.web.servlet.user;

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

@WebServlet(Constants.FIND_ALL_USER_URL)
public class FindAllServlet extends HttpServlet {
    private final UserService userService=new UserServiceImpl(InMemoryUserRepository.getInastance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users",userService.findAll());
        getServletContext().getRequestDispatcher(Constants.FIND_ALL_USER_PAGE_PATH).forward(req,resp);
    }
}
