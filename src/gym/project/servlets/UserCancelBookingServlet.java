package gym.project.servlets;

import gym.project.dao.BookingDao;
import gym.project.dto.UserDto;
import gym.project.mapper.BookMapper;
import gym.project.service.BookService;
import gym.project.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/userViewBookings/userCancelBooking")
public class UserCancelBookingServlet extends HttpServlet {
    private final BookService bookService = BookService.getINSTANCE();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("bookingId"));

        bookService.cancel(id);
        if (((UserDto)req.getSession().getAttribute("user")).getRole().equals("USER")) {
            resp.sendRedirect("/userViewBookings");
        } else {
            resp.sendRedirect("/adminBookingManagement");
        }

    }
}
