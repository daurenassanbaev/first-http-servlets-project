package gym.project.servlets;

import gym.project.service.AdminService;
import gym.project.service.BookService;
import gym.project.service.UserService;
import gym.project.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserViewServlet", urlPatterns = "/userView")
public class UserViewServlet extends HttpServlet {
    private final BookService bookService = BookService.getINSTANCE();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("schedules", bookService.getAllScheduleJoinView());
        req.getRequestDispatcher(JspHelper.getPath("userView")).forward(req, resp);
    }
}
