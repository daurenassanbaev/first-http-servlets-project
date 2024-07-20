package gym.project.servlets;

import gym.project.dto.UserDto;
import gym.project.service.BookService;
import gym.project.service.ReviewService;
import gym.project.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "UserReviewRatingsServlet", urlPatterns = "/userReviewRatings")
public class UserReviewRatingsServlet extends HttpServlet {
    private final BookService bookService = BookService.getINSTANCE();
    private final ReviewService reviewService = ReviewService.getINSTANCE();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = ((UserDto)req.getSession().getAttribute("user")).getId();
        req.setAttribute("schedules", bookService.getAllScheduleJoin(userId));
        req.setAttribute("reviews", reviewService.findAllViewReview());
        req.getRequestDispatcher(JspHelper.getPath("userReviewRatings")).forward(req, resp);
    }
}
