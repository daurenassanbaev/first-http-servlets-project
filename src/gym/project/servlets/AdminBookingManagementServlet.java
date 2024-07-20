package gym.project.servlets;

import gym.project.service.BookService;
import gym.project.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(name = "AdminBookingManagement", urlPatterns = "/adminBookingManagement")
public class AdminBookingManagementServlet extends HttpServlet {
    private final BookService bookService = BookService.getINSTANCE();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("bookings", bookService.getAdminBookings());
        req.getRequestDispatcher(JspHelper.getPath("adminBookingManagement")).forward(req, resp);
    }
}
