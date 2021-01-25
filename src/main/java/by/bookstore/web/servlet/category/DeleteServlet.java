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

@WebServlet(Constants.DELETE_CATEGORY_URL)
public class DeleteServlet extends HttpServlet {
    private CategoryService categoryService=new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories",categoryService.findAll());
        getServletContext().getRequestDispatcher(Constants.DELETE_CATEGORY_PAGE_PATH).forward(req,resp);
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
            getServletContext().getRequestDispatcher(Constants.DELETE_CATEGORY_PAGE_PATH).forward(req,resp);
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
        Category byId = categoryService.findById(id);
        if (byId == null) {
            req.setAttribute("id", id);
            req.setAttribute("message", "Category not found");
            getServletContext().getRequestDispatcher(Constants.DELETE_CATEGORY_PAGE_PATH).forward(req,resp);
        } else {
            categoryService.delete(id);
            resp.sendRedirect("/");
        }
    }
}
