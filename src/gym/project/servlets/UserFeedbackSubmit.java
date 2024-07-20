package gym.project.servlets;

import gym.project.dto.ReviewDto;
import gym.project.dto.UserDto;
import gym.project.exception.EmailValidationException;
import gym.project.service.ReviewService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;

@WebServlet("/userFeedbackSubmit")
public class UserFeedbackSubmit extends HttpServlet {
    private final ReviewService reviewService = ReviewService.getINSTANCE();
    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ReviewDto reviewDto = ReviewDto.builder()
                .userId(((UserDto) req.getSession().getAttribute("user")).getId())
                .trainerId(Integer.parseInt(req.getParameter("trainerId")))
                .rating(Integer.parseInt(req.getParameter("rating")))
                .comment(req.getParameter("comment"))
                .build();
        try {
            reviewService.create(reviewDto);
            resp.sendRedirect("/userReviewRatings");
        } finally {

        }
    }
}
