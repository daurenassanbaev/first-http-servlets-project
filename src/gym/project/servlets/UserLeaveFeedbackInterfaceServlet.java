package gym.project.servlets;

import gym.project.entity.Trainer;
import gym.project.service.AdminService;
import gym.project.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/userReviewRatings/userLeaveFeedback")
public class UserLeaveFeedbackInterfaceServlet extends HttpServlet {
    AdminService adminService = AdminService.getINSTANCE();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer trainerId = Integer.parseInt(req.getParameter("trainerId"));
        Trainer trainer = adminService.findTrainerById(trainerId);
        req.setAttribute("trainer", trainer);
        req.getRequestDispatcher(JspHelper.getPath("userLeaveFeedback")).forward(req, resp);
    }
}
