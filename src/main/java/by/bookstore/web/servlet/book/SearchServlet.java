package by.bookstore.web.servlet.book;

import by.bookstore.entity.Book;
import by.bookstore.repository.inmemory.InMemoryBookRepository;
import by.bookstore.service.BookService;
import by.bookstore.service.BookServiceImpl;
import by.bookstore.web.servlet.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(Constants.SEARCH_URL)
public class SearchServlet extends HttpServlet {
    private final BookService bookService=new BookServiceImpl(InMemoryBookRepository.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("query");
        List<Book> byTitle = bookService.search(query);
        req.setAttribute("books",byTitle);
        if(byTitle.size() == 0){
            req.setAttribute("message","not found");
        }
        getServletContext().getRequestDispatcher(Constants.SEARCH_PAGE_PATH).forward(req,resp);
    }

}
