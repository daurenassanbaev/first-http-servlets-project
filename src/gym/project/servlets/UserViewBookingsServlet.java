package gym.project.servlets;

import gym.project.dto.UserDto;
import gym.project.service.BookService;
import gym.project.service.UserService;
import gym.project.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(name = "UserViewBookingsServlet", urlPatterns = "/userViewBookings")
public class UserViewBookingsServlet extends HttpServlet {
    private final BookService bookService = BookService.getINSTANCE();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = ((UserDto)req.getSession().getAttribute("user")).getId();
        req.setAttribute("schedules", bookService.getAllScheduleJoin(userId));
        req.getRequestDispatcher(JspHelper.getPath("userViewBookings")).forward(req, resp);
    }
}
