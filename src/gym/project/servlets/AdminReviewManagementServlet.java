package gym.project.servlets;

import gym.project.service.ReviewService;
import gym.project.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AdminReviewManagement", urlPatterns = "/adminReviewManagement")
public class AdminReviewManagementServlet extends HttpServlet {
    private final ReviewService reviewService = ReviewService.getINSTANCE();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("reviews", reviewService.findAllViewReviewAdmin());
        req.getRequestDispatcher(JspHelper.getPath("adminReviewManagement")).forward(req, resp);
    }
}
