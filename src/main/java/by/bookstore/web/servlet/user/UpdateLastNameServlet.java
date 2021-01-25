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

@WebServlet(Constants.UPDATE_LAST_NAME_USER_URL)
public class UpdateLastNameServlet extends HttpServlet {
    private final UserService userService=new UserServiceImpl(InMemoryUserRepository.getInastance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users",userService.findAll());
        getServletContext().getRequestDispatcher(Constants.UPDATE_LAST_NAME_USER_PAGE_PATH).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId=Integer.parseInt(req.getParameter("userId"));
        String lastName = req.getParameter("newLastName");

        if(valid(lastName,req)){
            update(lastName,userId,resp);
        }
    }

    private boolean valid(String lastName, HttpServletRequest req){
        if(lastName.isEmpty()||lastName.isBlank()){
            req.setAttribute("message","New last name is empty ");
            return false;
        }
        return true;
    }

    private void update(String lastName, int userId, HttpServletResponse resp) throws IOException{
        userService.updateLastName(lastName,userId);
        resp.sendRedirect("/");
    }
}
