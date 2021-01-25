package by.bookstore.web.servlet.city;

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

@WebServlet(Constants.FIND_ALL_CITY_URL)
public class FindAll extends HttpServlet {
    private final CityService cityService=new CityServiceImpl(InMemoryCityRepository.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("message",cityService.findAll());
        getServletContext().getRequestDispatcher(Constants.FIND_ALL_CITY_PAGE_PATH).forward(req,resp);
    }
}
