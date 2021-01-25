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

@WebServlet(Constants.SAVE_ORDER_URL)
public class SaveServlet extends HttpServlet {
    private final OrderService orderService=new OrderServiceImpl(InMemoryOrderRepository.getInstance());
    private final StoreService storeService=new StoreServiceImpl(InMemoryStoreRepository.getInstance());
    private final UserService userService=new UserServiceImpl(InMemoryUserRepository.getInastance());
    private final AddressService addressService=new AddressServiceImpl(InMemoryAddressRepository.getInsatnce());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users",userService.findAll());
        req.setAttribute("addresses",addressService.findAll());
        req.setAttribute("stores",storeService.findAll());
        getServletContext().getRequestDispatcher(Constants.SAVE_ORDER_PAGE_PATH).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String storeId = req.getParameter("storeId");
        String userId = req.getParameter("userId");
        String address=req.getParameter("addressId");
        int userId1=0;
        int storeId1=0;
        int addressId1=0;

        if(!storeId.isEmpty()||!storeId.isBlank()){
            storeId1=Integer.parseInt(storeId);
        }

        if(!userId.isEmpty()||!userId.isBlank()){
            userId1=Integer.parseInt(userId);
        }

        if(!address.isEmpty()||!address.isBlank()){
            addressId1=Integer.parseInt(address);
        }

        if(isValid(storeId,userId,address,req)){
           save(userId1, storeId1, addressId1, req, resp);
        } else {
            req.setAttribute("userId",userId1);
            req.setAttribute("addressId",addressId1);
            req.setAttribute("storeId",storeId1);
            getServletContext().getRequestDispatcher(Constants.SAVE_ORDER_PAGE_PATH).forward(req,resp);
        }
    }

    private boolean isValid(String storeId, String userId, String address, HttpServletRequest req){
        if(storeId.isBlank()||storeId.isEmpty()){
            req.setAttribute("message","storeId is empty");
            return false;
        }

        if(userId.isEmpty()||userId.isBlank()){
            req.setAttribute("message", "userId is empty");
            return false;
        }

        if(address.isBlank()||address.isEmpty()){
            req.setAttribute("message","address is empty");
            return false;
        }
        return true;
    }

    private void save(int userId1, int storeId1, int addressId1, HttpServletRequest req, HttpServletResponse resp) throws IOException{
        User userById = userService.findById(userId1);
        Store storeById = storeService.findById(storeId1);
        Address addressById = addressService.findById(addressId1);
        Basket basket = (Basket)req.getSession().getAttribute("basket");
        Book[] books = basket.getBooks();
        String isDelivery = req.getParameter("isDelivery");
        boolean b = Boolean.parseBoolean(isDelivery);
        Order order=new Order(storeById,userById,books,Status.ACTIVE, b,addressById, Calendar.getInstance().getTime());
        orderService.add(order);
        resp.sendRedirect("/");
    }
}
