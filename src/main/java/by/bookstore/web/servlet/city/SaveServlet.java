package by.bookstore.web.servlet.city;

import by.bookstore.entity.City;
import by.bookstore.repository.inmemory.InMemoryCityRepository;
import by.bookstore.service.CityService;
import by.bookstore.service.CityServiceImpl;
import by.bookstore.web.servlet.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(Constants.SAVE_CITY_URL)
public class SaveServlet extends HttpServlet {
    private final CityService cityService=new CityServiceImpl(InMemoryCityRepository.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(Constants.SAVE_CITY_PAGE_PATH).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");

        if(valid(name,req)){
            save(name,req,resp);
        } else {
            req.setAttribute("name",name);
            getServletContext().getRequestDispatcher(Constants.SAVE_CITY_PAGE_PATH).forward(req,resp);
        }
    }

    private boolean valid(String name,HttpServletRequest req){
        if(name.isEmpty()||name.isBlank()){
            req.setAttribute("message","name is empty");
            return false;
        }
        return true;
    }

    private void save(String name,HttpServletRequest req,HttpServletResponse resp) throws IOException,ServletException{
        City city=new City(name);
        cityService.add(city);
        resp.sendRedirect("/");
    }
}
