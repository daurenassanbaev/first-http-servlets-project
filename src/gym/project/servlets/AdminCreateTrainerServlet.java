package gym.project.servlets;

import gym.project.dto.TrainerDto;
import gym.project.exception.TrainerValidationException;
import gym.project.service.AdminService;
import gym.project.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet(name = "AdminCreateTrainerServlet", urlPatterns = "/adminCoach/adminCreateTrainer")
public class AdminCreateTrainerServlet extends HttpServlet {
    private static final AdminService adminService = AdminService.getINSTANCE();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("adminCreateTrainer")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TrainerDto trainerDto = TrainerDto.builder()
                .name(req.getParameter("name"))
                .bio(req.getParameter("bio"))
                .contactPhone(req.getParameter("contact_phone"))
                .contactEmail(req.getParameter("contact_email"))
                .photo(req.getPart("photo"))
                .build();
        try {
            adminService.createTrainer(trainerDto);
            resp.sendRedirect("/adminCoach");
        } catch (TrainerValidationException e) {
            req.setAttribute("error", e.getMessage());
            doGet(req, resp);
        }
    }
}
