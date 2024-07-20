package gym.project.servlets;

import gym.project.dto.ScheduleDto;
import gym.project.dto.TrainerDto;
import gym.project.entity.Schedule;
import gym.project.entity.Trainer;
import gym.project.exception.EmailValidationException;
import gym.project.exception.TrainerValidationException;
import gym.project.mapper.ScheduleMapper;
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

@WebServlet("/adminCoach/adminViewAllSchedules/adminEditSchedule")
public class AdminEditScheduleServlet extends HttpServlet {
    private final AdminService adminService = AdminService.getINSTANCE();
    private final ScheduleMapper scheduleMapper = ScheduleMapper.getINSTANCE();
    private Integer id;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Schedule schedule = adminService.findScheduleById(Integer.parseInt(req.getParameter("scheduleId")));
        id = Integer.parseInt(req.getParameter("scheduleId"));
        req.setAttribute("schedule", schedule);
        req.setAttribute("trainers", adminService.getAllTrainers());
        req.getRequestDispatcher(JspHelper.getPath("adminEditSchedule")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ScheduleDto scheduleDto = ScheduleDto.builder()
                .id(id)
                .trainerId(Integer.parseInt(req.getParameter("trainer_id")))
                .name(req.getParameter("name"))
                .date(LocalDate.parse(req.getParameter("date"), DATE_FORMATTER))
                .startTime(LocalTime.parse(req.getParameter("start_time"), TIME_FORMATTER))
                .endTime(LocalTime.parse(req.getParameter("end_time"), TIME_FORMATTER))
                .location(req.getParameter("location"))
                .build();
        try {
            adminService.updateSchedule(scheduleDto);
            resp.sendRedirect("/adminCoach/adminViewAllSchedules");
        } catch (EmailValidationException e) {
            Schedule schedule = scheduleMapper.mapFrom(scheduleDto);
            req.setAttribute("schedule", schedule);
            req.setAttribute("error", e.getMessage());
            req.setAttribute("trainers", adminService.getAllTrainers());
            req.getRequestDispatcher(JspHelper.getPath("adminEditSchedule")).forward(req, resp);
        }
    }
}
