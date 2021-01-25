package by.bookstore.web.servlet.store;

import by.bookstore.entity.Address;
import by.bookstore.entity.City;
import by.bookstore.entity.Store;
import by.bookstore.repository.inmemory.InMemoryAddressRepository;
import by.bookstore.repository.inmemory.InMemoryCityRepository;
import by.bookstore.repository.inmemory.InMemoryStoreRepository;
import by.bookstore.service.*;
import by.bookstore.web.servlet.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(Constants.SAVE_STORE_URL)
public class SaveServlet extends HttpServlet {
    private final StoreService storeService=new StoreServiceImpl(InMemoryStoreRepository.getInstance());
    private final AddressService addressService=new AddressServiceImpl(InMemoryAddressRepository.getInsatnce());
    private final CityService cityService=new CityServiceImpl(InMemoryCityRepository.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("addresses",addressService.findAll());
        req.setAttribute("cities",cityService.findAll());
        getServletContext().getRequestDispatcher(Constants.SAVE_STORE_PAGE_PATH).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int addressId=Integer.parseInt(req.getParameter("addressId"));
        String cityName = req.getParameter("cityName");

       if(valid(name,req)){
           save(cityName,addressId,name,resp);
       } else {
           req.setAttribute("name",name);
           req.setAttribute("addressId",addressId);
           req.setAttribute("cityName",cityName);
           getServletContext().getRequestDispatcher(Constants.SAVE_STORE_PAGE_PATH).forward(req,resp);
       }
    }

    private boolean valid(String name,HttpServletRequest req){
        if(name.isEmpty()||name.isBlank()){
            req.setAttribute("message","name is empty");
            return false;
        }
        return true;
    }

    private void save(String cityName,int addressId,String name,HttpServletResponse resp) throws IOException,ServletException{
        City cityByName = cityService.findByName(cityName);
        Address addressById = addressService.findById(addressId);
        Store store=new Store(name,cityByName,addressById);

        storeService.add(store);
        resp.sendRedirect("/");
    }
}
