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

@WebServlet(Constants.FIND_BY_TITLE_BOOK_URL)
public class FindByTitleServlet extends HttpServlet {
    private final BookService bookService=new BookServiceImpl(InMemoryBookRepository.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(Constants.FIND_BY_TITLE_BOOK_PAGE_PATH).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");

        if(valid(title,req)){
            find(title,req,resp);
        } else {
            getServletContext().getRequestDispatcher(Constants.FIND_BY_TITLE_BOOK_PAGE_PATH).forward(req,resp);
        }
    }

    private boolean valid(String title,HttpServletRequest req){
        if(title.isEmpty()||title.isBlank()){
            req.setAttribute("message","title is empty");
            return false;
        }
        return true;
    }

    private void find(String title,HttpServletRequest req,HttpServletResponse resp) throws IOException,ServletException{
        Book byTitle = bookService.findByTitle(title);
        if(byTitle==null){
            req.setAttribute("title",title);
            req.setAttribute("message", "Book not found");
        } else {
            req.setAttribute("book" ,byTitle);
        }
        getServletContext().getRequestDispatcher(Constants.FIND_BY_TITLE_BOOK_PAGE_PATH).forward(req,resp);
    }
}
