package by.bookstore.web.servlet.order;

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

@WebServlet(Constants.VIEW_ORDER_URL)
public class ViewServlet extends HttpServlet {
    private final OrderService orderService=new OrderServiceImpl(InMemoryOrderRepository.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=Integer.parseInt(req.getParameter("id"));
        req.setAttribute("order", orderService.findById(id));
        getServletContext().getRequestDispatcher(Constants.VIEW_ORDER_PAGE_PATH).forward(req,resp);
    }
}
