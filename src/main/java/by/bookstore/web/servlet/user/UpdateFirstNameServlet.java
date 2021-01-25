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

@WebServlet(Constants.UPDATE_FIRST_NAME_USER_URL)
public class UpdateFirstNameServlet extends HttpServlet {
    private final UserService userService=new UserServiceImpl(InMemoryUserRepository.getInastance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users",userService.findAll());
        getServletContext().getRequestDispatcher(Constants.UPDATE_FIRST_NAME_USER_PAGE_PATH).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId=Integer.parseInt(req.getParameter("userId"));
        String newName = req.getParameter("newName");

        if(valid(newName,req)){
            update(newName,userId,resp);
        }
    }

    private boolean valid(String newName,HttpServletRequest req){
        if(newName.isEmpty()||newName.isBlank()){
            req.setAttribute("message","name is empty");
            return false;
        }
        return true;
    }

    private void update(String newName,int userId,HttpServletResponse resp) throws IOException{
        userService.updateFirstName(newName,userId);
        resp.sendRedirect("/");
    }
}
