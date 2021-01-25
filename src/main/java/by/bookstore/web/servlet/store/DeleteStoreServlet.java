package by.bookstore.web.servlet.store;

import by.bookstore.repository.inmemory.InMemoryStoreRepository;
import by.bookstore.service.StoreService;
import by.bookstore.service.StoreServiceImpl;
import by.bookstore.web.servlet.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(Constants.DELETE_STORE_URL)
public class DeleteStoreServlet extends HttpServlet {
    private final StoreService storeService=new StoreServiceImpl(InMemoryStoreRepository.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("stores",storeService.findAll());
        getServletContext().getRequestDispatcher(Constants.DELETE_STORE_PAGE_PATH).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int storeId=Integer.parseInt(req.getParameter("storeId"));

        if(valid(storeId,req)){
            delete(storeId,resp);
        } else {
            req.setAttribute("storeId",storeId);
            getServletContext().getRequestDispatcher(Constants.DELETE_CITY_PAGE_PATH).forward(req,resp);
        }

    }

    private boolean valid(int id,HttpServletRequest req){
        if(id<1){
            req.setAttribute("message", "negative id");
            return false;
        }
        return true;
    }

    private void delete(int storeId,HttpServletResponse resp) throws IOException{
        storeService.delete(storeId);
        resp.sendRedirect("/");
    }
}
