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

@WebServlet(Constants.FIND_ALL_STORE_URL)
public class FindAllServlet extends HttpServlet {
    private final StoreService storeService=new StoreServiceImpl(InMemoryStoreRepository.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("stores",storeService.findAll());
        getServletContext().getRequestDispatcher(Constants.FIND_ALL_STORE_PAGE_PATH).forward(req,resp);
    }
}
