package gym.project.servlets;

import gym.project.dto.ScheduleDto;
import gym.project.exception.EmailValidationException;
import gym.project.service.AdminService;
import gym.project.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/adminCoach/adminCreateSchedule")
public class AdminCreateScheduleServlet extends HttpServlet {
    private final AdminService adminService = AdminService.getINSTANCE();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("trainers", adminService.getAllTrainers());
        req.getRequestDispatcher(JspHelper.getPath("adminCreateSchedule")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ScheduleDto scheduleDto = ScheduleDto.builder()
                .trainerId(Integer.parseInt(req.getParameter("trainer_id")))
                .name(req.getParameter("name"))
                .date(LocalDate.parse(req.getParameter("date"), DATE_FORMATTER))
                .startTime(LocalTime.parse(req.getParameter("start_time"), TIME_FORMATTER))
                .endTime(LocalTime.parse(req.getParameter("end_time"), TIME_FORMATTER))
                .location(req.getParameter("location"))
                .build();
        try {
            adminService.createSchedule(scheduleDto);
            resp.sendRedirect("/adminCoach");
        } catch (EmailValidationException e) {
            req.setAttribute("error", e.getMessage());
            doGet(req, resp);
        }

    }
}
