package by.bookstore.web.servlet.address;

import by.bookstore.repository.inmemory.InMemoryAddressRepository;
import by.bookstore.service.AddressService;
import by.bookstore.service.AddressServiceImpl;
import by.bookstore.web.servlet.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(Constants.FIND_ALL_ADDRESS_URL)
public class FindAll extends HttpServlet {
    private final AddressService addressService=new AddressServiceImpl(InMemoryAddressRepository.getInsatnce());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("message",addressService.findAll());
        getServletContext().getRequestDispatcher(Constants.FIND_ALL_ADDRESS_PAGE_PATH).forward(req,resp);
    }
}
