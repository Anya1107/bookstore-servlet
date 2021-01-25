package by.bookstore.web.servlet.store;

import by.bookstore.entity.Address;
import by.bookstore.entity.Store;
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

@WebServlet(Constants.FIND_BY_NAME_STORE_URL)
public class FindByNameServlet extends HttpServlet {
    private final StoreService storeService=new StoreServiceImpl(InMemoryStoreRepository.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(Constants.FIND_BY_NAME_STORE_PAGE_PATH).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");

        if (valid(name,req)) {
            find(name,req,resp);
        } else {
            getServletContext().getRequestDispatcher(Constants.FIND_BY_NAME_STORE_PAGE_PATH).forward(req,resp);
        }
    }

    private boolean valid(String name,HttpServletRequest req){
        if (name.isEmpty() || name.isBlank()) {
            req.setAttribute("message", "name is empty");
            return false;
        }
        return true;
    }

    private void find(String name,HttpServletRequest req,HttpServletResponse resp) throws IOException,ServletException{
        Store byName = storeService.findByName(name);
        if (byName == null) {
            req.setAttribute("name", name);
            req.setAttribute("message", "Store not found");
        } else {
            req.setAttribute("store", byName);
        }
        getServletContext().getRequestDispatcher(Constants.FIND_BY_NAME_STORE_PAGE_PATH).forward(req,resp);
    }
}
