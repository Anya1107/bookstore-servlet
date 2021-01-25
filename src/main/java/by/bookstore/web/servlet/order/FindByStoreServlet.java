package by.bookstore.web.servlet.order;

import by.bookstore.entity.Order;
import by.bookstore.entity.Store;
import by.bookstore.repository.inmemory.InMemoryOrderRepository;
import by.bookstore.repository.inmemory.InMemoryStoreRepository;
import by.bookstore.service.OrderService;
import by.bookstore.service.OrderServiceImpl;
import by.bookstore.service.StoreService;
import by.bookstore.service.StoreServiceImpl;
import by.bookstore.web.servlet.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(Constants.FIND_BY_STORE_ORDER_URL)
public class FindByStoreServlet extends HttpServlet {
    private final OrderService orderService=new OrderServiceImpl(InMemoryOrderRepository.getInstance());
    private final StoreService storeService=new StoreServiceImpl(InMemoryStoreRepository.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("stores",storeService.findAll());
        getServletContext().getRequestDispatcher(Constants.FIND_BY_STORE_ORDER_PAGE_PATH).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String storeId = req.getParameter("storeId");
        int storeId1=0;

        if(!storeId.isEmpty()||!storeId.isBlank()){
            storeId1=Integer.parseInt(storeId);
        }

        if(isValid(storeId,req)){
            find(storeId1,req,resp);
        }
    }

    private boolean isValid(String storeId,HttpServletRequest req){
        if(storeId.isBlank()||storeId.isEmpty()){
            req.setAttribute("message","storeId is empty");
            return false;
        }
        return true;
    }

    private void find(int storeId,HttpServletRequest req,HttpServletResponse resp)throws IOException,ServletException{
        Store store = storeService.findById(storeId);
        Order byStore = orderService.findByStore(store);
        if (byStore == null) {
            req.setAttribute("storeId", storeId);
            req.setAttribute("message", "Order not found");
        } else {
            req.setAttribute("order", byStore);
        }
        getServletContext().getRequestDispatcher(Constants.FIND_BY_STORE_ORDER_PAGE_PATH).forward(req, resp);
    }
}
