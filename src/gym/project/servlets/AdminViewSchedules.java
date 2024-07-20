package gym.project.servlets;

import gym.project.service.AdminService;
import gym.project.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/adminCoach/adminViewAllSchedules")
public class AdminViewSchedules extends HttpServlet {
    private final AdminService adminService = AdminService.getINSTANCE();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("schedules", adminService.getAllSchedules());
        req.getRequestDispatcher(JspHelper.getPath("adminViewAllSchedules")).forward(req, resp);
    }
}
