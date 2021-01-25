package by.bookstore.web.servlet.book;

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

@WebServlet(Constants.DELETE_BOOK_URL)
public class DeleteBookServlet extends HttpServlet {
    private final BookService bookService=new BookServiceImpl(InMemoryBookRepository.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("books",bookService.findAll());
        getServletContext().getRequestDispatcher(Constants.DELETE_BOOK_PAGE_PATH).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=Integer.parseInt(req.getParameter("bookId"));

        if(valid(id,req)){
            delete(id,resp);
        } else {
            req.setAttribute("bookId",id);
            getServletContext().getRequestDispatcher(Constants.DELETE_BOOK_PAGE_PATH).forward(req,resp);
        }
    }

    private boolean valid(int id,HttpServletRequest req){
        if(id<1){
            req.setAttribute("message","negative number");
            return false;
        }
        return true;
    }

    private void delete(int id,HttpServletResponse resp) throws IOException{
        bookService.delete(id);
        resp.sendRedirect("/");
    }
}
