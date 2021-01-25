package by.bookstore.web.servlet.order;

import by.bookstore.entity.*;
import by.bookstore.repository.inmemory.InMemoryAddressRepository;
import by.bookstore.repository.inmemory.InMemoryOrderRepository;
import by.bookstore.repository.inmemory.InMemoryStoreRepository;
import by.bookstore.repository.inmemory.InMemoryUserRepository;
import by.bookstore.service.*;
import by.bookstore.web.servlet.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

@WebServlet(Constants.CREATE_ORDER_URL)
public class SaveOrderByUserServlet extends HttpServlet {
    private final OrderService orderService=new OrderServiceImpl(InMemoryOrderRepository.getInstance());
    private final StoreService storeService=new StoreServiceImpl(InMemoryStoreRepository.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("stores",storeService.findAll());
        getServletContext().getRequestDispatcher(Constants.CREATE_ORDER_PAGE_PATH).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String storeId = req.getParameter("storeId");
        String address=req.getParameter("address");
        int storeId1=0;
        User user = (User) req.getSession().getAttribute("user");
        Address address1=new Address(address);
        Store storeById = storeService.findById(storeId1);
        Basket basket = (Basket)req.getSession().getAttribute("basket");
        Book[] books = basket.getBooks();
        String isDelivery = req.getParameter("isDelivery");
        boolean b = Boolean.parseBoolean(isDelivery);


        if(isValid(storeId, storeId1, address, b, req)){
            save(storeById, address1, b, user,books, req, resp);
        } else {
            req.setAttribute("storeId",storeId1);
            getServletContext().getRequestDispatcher(Constants.CREATE_ORDER_PAGE_PATH).forward(req,resp);
        }
    }

    private boolean isValid(String storeId, int storeId1, String address, boolean b, HttpServletRequest req){
        if(!b){
            if(!storeId.isEmpty()||!storeId.isBlank()){
                storeId1=Integer.parseInt(storeId);
            } else {
                req.setAttribute("message","storeId is empty");
                return false;
            }
        }

        if(address.isBlank()||address.isEmpty()){
            req.setAttribute("message","address is empty");
            return false;
        }
        return true;
    }

    private void save(Store storeById, Address address1, boolean b, User user, Book[] books, HttpServletRequest req, HttpServletResponse resp) throws IOException{
        if(b){
            Order order=new Order(null,user,books,Status.ACTIVE,b,address1,Calendar.getInstance().getTime());
            orderService.add(order);
        } else{
            Order order=new Order(storeById,user,books,Status.ACTIVE, b,address1, Calendar.getInstance().getTime());
            orderService.add(order);
        }
        req.getSession().setAttribute("basket", new Basket(new Book[10]));
        resp.sendRedirect("/");
    }
}
