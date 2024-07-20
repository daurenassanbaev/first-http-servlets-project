package gym.project.servlets;

import gym.project.dto.BookingDto;
import gym.project.dto.TrainerDto;
import gym.project.dto.UserDto;
import gym.project.exception.TrainerValidationException;
import gym.project.service.BookService;
import gym.project.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;

@WebServlet("/userBookingTraining/userBookTrainer")
public class UserBookTrainer extends HttpServlet {
    private final BookService bookService = BookService.getINSTANCE();
    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookingDto bookingDto = BookingDto.builder()
                .userId(((UserDto)req.getSession().getAttribute("user")).getId())
                .scheduleId(Integer.parseInt(req.getParameter("scheduleId")))
                .build();
        try {
            bookService.book(bookingDto);
            resp.sendRedirect("/userBookingTraining");
        } finally {

        }
    }
}
