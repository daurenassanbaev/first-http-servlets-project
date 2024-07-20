package gym.project.servlets;

import gym.project.service.AdminService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/adminCoach/adminViewAllSchedules/adminDeleteSchedule")
public class AdminDeleteScheduleServlet extends HttpServlet {
    private final AdminService adminService = AdminService.getINSTANCE();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer trainerId = Integer.parseInt(req.getParameter("scheduleId"));
        Integer id = adminService.deleteSchedule(trainerId);
        if (id >= 1) {
            resp.sendRedirect("/adminCoach/adminViewAllSchedules");
        }
    }
}
