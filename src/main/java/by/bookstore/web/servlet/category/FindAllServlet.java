package by.bookstore.web.servlet.category;

import by.bookstore.service.CategoryService;
import by.bookstore.service.CategoryServiceImpl;
import by.bookstore.web.servlet.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(Constants.FIND_ALL_CATEGORY_URL)
public class FindAllServlet extends HttpServlet {
    private final CategoryService categoryService=new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories",categoryService.findAll());
        getServletContext().getRequestDispatcher(Constants.FIND_ALL_CATEGORY_PAGE_PATH).forward(req,resp);
    }
}
