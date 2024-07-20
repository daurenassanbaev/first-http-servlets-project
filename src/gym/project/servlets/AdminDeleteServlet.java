package gym.project.servlets;

import gym.project.service.AdminService;
import gym.project.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/adminCoach/adminViewAll/adminDelete")
public class AdminDeleteServlet extends HttpServlet {
    private final AdminService adminService = AdminService.getINSTANCE();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer trainerId = Integer.parseInt(req.getParameter("trainerId"));
        Integer id = adminService.deleteTrainer(trainerId);
        if (id >= 1) {
            resp.sendRedirect("/adminCoach/adminViewAll");
        }
    }
}
