package by.bookstore.web.servlet.address;

import by.bookstore.entity.Address;
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

@WebServlet(Constants.SAVE_ADDRESS_URL)
public class SaveServlet extends HttpServlet {
    private final AddressService addressService=new AddressServiceImpl(InMemoryAddressRepository.getInsatnce());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(Constants.SAVE_ADDRESS_PAGE_PATH).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");

        if(valid(name,req)){
            save(name,resp);
        } else {
            req.setAttribute("name", name);
            getServletContext().getRequestDispatcher(Constants.SAVE_ADDRESS_PAGE_PATH).forward(req,resp);
        }
    }

    private boolean valid(String name,HttpServletRequest req){
        if(name.isEmpty()||name.isBlank()){
            req.setAttribute("message","name is empty");
            return false;
        }
        return true;
    }

    private void save(String name,HttpServletResponse resp) throws IOException{
        Address address=new Address(name);
        addressService.add(address);
        resp.sendRedirect("/");
    }
}
