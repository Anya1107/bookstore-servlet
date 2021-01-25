package by.bookstore.web.servlet.user;

import by.bookstore.entity.User;
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

@WebServlet(Constants.ACCOUNT_URL)
public class AccountServlet extends HttpServlet {
    private final OrderService orderService=new OrderServiceImpl(InMemoryOrderRepository.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("orders",orderService.findAll());
        getServletContext().getRequestDispatcher(Constants.ACCOUNT_PAGE_PATH).forward(req,resp);
    }
}
