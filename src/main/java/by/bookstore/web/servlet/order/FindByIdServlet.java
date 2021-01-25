package by.bookstore.web.servlet.order;

import by.bookstore.entity.Order;
import by.bookstore.entity.Store;
import by.bookstore.repository.inmemory.InMemoryOrderRepository;
import by.bookstore.service.OrderService;
import by.bookstore.service.OrderServiceImpl;
import by.bookstore.web.servlet.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(Constants.FIND_BY_ID_ORDER_URL)
public class FindByIdServlet extends HttpServlet {
    private final OrderService orderService=new OrderServiceImpl(InMemoryOrderRepository.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(Constants.FIND_ALL_BY_ID_ORDER_PAGE_PATH).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id1 = req.getParameter("id");
        int id=0;

        if(!id1.isEmpty()||!id1.isBlank()){
            id=Integer.parseInt(id1);
        }

        if (valid(id1,id,req)) {
            find(id,req,resp);
        } else {
            getServletContext().getRequestDispatcher(Constants.FIND_ALL_BY_ID_ORDER_PAGE_PATH).forward(req,resp);
        }
    }

    private boolean valid(String id1,int id,HttpServletRequest req){
        if (id1.isEmpty()||id1.isBlank()) {
            req.setAttribute("message", "Id is empty");
            return false;
        }else {
            if (id < 1) {
                req.setAttribute("message", "negative id");
                return false;
            }
            return true;
        }
    }

    private void find(int id,HttpServletRequest req,HttpServletResponse resp) throws IOException,ServletException{
        Order byId = orderService.findById(id);
        if (byId == null) {
            req.setAttribute("id", id);
            req.setAttribute("message", "Order not found");
        } else {
            req.setAttribute("order", byId);
        }
        getServletContext().getRequestDispatcher(Constants.FIND_ALL_BY_ID_ORDER_PAGE_PATH).forward(req, resp);
    }
}

