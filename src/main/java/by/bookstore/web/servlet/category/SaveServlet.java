package by.bookstore.web.servlet.category;

import by.bookstore.entity.Category;
import by.bookstore.service.CategoryService;
import by.bookstore.service.CategoryServiceImpl;
import by.bookstore.web.servlet.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(Constants.SAVE_CATEGORY_URL)
public class SaveServlet extends HttpServlet {
    private final CategoryService categoryService=new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(Constants.SAVE_CATEGORY_PAGE_PATH).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");

        if(valid(name,req)){
            Category category=new Category(name);
            categoryService.add(category);
            resp.sendRedirect("/");
        } else {
            req.setAttribute("name",name);
            getServletContext().getRequestDispatcher(Constants.SAVE_CATEGORY_PAGE_PATH).forward(req,resp);
        }
    }

    private boolean valid(String name,HttpServletRequest req){
        if(name.isEmpty()||name.isBlank()){
            req.setAttribute("message", "name is empty");
            return false;
        }
        return true;
    }
}
