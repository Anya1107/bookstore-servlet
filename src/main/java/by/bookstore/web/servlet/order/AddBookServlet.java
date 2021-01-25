package by.bookstore.web.servlet.order;

import by.bookstore.entity.*;
import by.bookstore.repository.inmemory.*;
import by.bookstore.service.*;
import by.bookstore.web.servlet.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(Constants.ADD_BOOK_IN_ORDER)
public class AddBookServlet extends HttpServlet {
    private final BookService bookService=new BookServiceImpl(InMemoryBookRepository.getInstance());
    private final OrderService orderService=new OrderServiceImpl(InMemoryOrderRepository.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        req.setAttribute("books",bookService.findAll());
        req.setAttribute("orderId", Integer.parseInt(orderId));
        getServletContext().getRequestDispatcher(Constants.ADD_BOOK_IN_ORDER_PAGE_PATH).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
        int id1=0;
        String orderId=req.getParameter("orderId");
        int id2=0;

        if(!id.isEmpty()||!id.isBlank()){
            id1=Integer.parseInt(id);
        }

        if(!orderId.isEmpty()||!orderId.isBlank()){
            id2=Integer.parseInt(orderId);
        }

        if(valid(id, orderId, id1,req)){
            add(id1,id2,req,resp);
        } else{
            req.setAttribute("id",id);
            getServletContext().getRequestDispatcher(Constants.ADD_BOOK_IN_ORDER_PAGE_PATH).forward(req,resp);
        }
    }

    private boolean valid(String id, String orderId, int id1,HttpServletRequest req){
        if (id.isEmpty()||id.isBlank()) {
            req.setAttribute("message", "Id is empty");
            return false;
        } else {
            if (id1 < 1) {
                req.setAttribute("message", "negative id");
                return false;
            }
        }
        if (orderId.isEmpty()||orderId.isBlank()) {
            req.setAttribute("message", "Id is empty");
            return false;
        }
        return true;
    }

    private void add(int id, int orderId, HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        Book byId = bookService.findById(id);
        if (byId == null) {
            req.setAttribute("id", id);
            req.setAttribute("message", "Book not found");
            getServletContext().getRequestDispatcher(Constants.ADD_BOOK_IN_ORDER_PAGE_PATH).forward(req,resp);
        } else {
            orderService.addBook(orderId,byId);
            resp.sendRedirect("/");
        }
    }
}

