package gym.project.servlets;

import gym.project.service.ReviewService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/adminDeleteReview")
public class AdminDeleteReviewServlet extends HttpServlet {
    private final ReviewService reviewService = ReviewService.getINSTANCE();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        reviewService.delete(Integer.parseInt(req.getParameter("reviewId")));
        resp.sendRedirect("/adminReviewManagement");
    }
}
