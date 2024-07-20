package gym.project.servlets;

import gym.project.dao.TrainerDao;
import gym.project.dto.TrainerDto;
import gym.project.entity.Trainer;
import gym.project.exception.EmailValidationException;
import gym.project.exception.TrainerValidationException;
import gym.project.mapper.TrainerMapper;
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

@WebServlet(name = "AdminEditTrainerServlet", urlPatterns = "/adminCoach/adminViewAll/adminEditTrainer")
public class AdminEditTrainerServlet extends HttpServlet {
    private final TrainerDao trainerDao = new TrainerDao();
    private final AdminService adminService = AdminService.getINSTANCE();
    private Integer id;
    TrainerMapper trainerMapper = TrainerMapper.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Trainer trainer = adminService.findTrainerById(Integer.parseInt(req.getParameter("trainerId")));
        id = Integer.parseInt(req.getParameter("trainerId"));
        req.setAttribute("trainer", trainer);
        req.getRequestDispatcher(JspHelper.getPath("adminEditTrainer")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TrainerDto trainerDto = TrainerDto.builder()
                .id(id)
                .name(req.getParameter("name"))
                .bio(req.getParameter("bio"))
                .contactPhone(req.getParameter("contact_phone"))
                .contactEmail(req.getParameter("contact_email"))
                .photo(req.getPart("photo"))
                .build();
        try {
            adminService.updateTrainer(trainerDto);
            resp.sendRedirect("/adminCoach/adminViewAll");
        } catch (TrainerValidationException e) {
            Trainer trainer = trainerMapper.mapFrom(trainerDto);
            req.setAttribute("trainer", trainer);
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher(JspHelper.getPath("adminEditTrainer")).forward(req, resp);
        } catch (EmailValidationException e) {
            Trainer trainer = trainerMapper.mapFrom(trainerDto);
            req.setAttribute("trainer", trainer);
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher(JspHelper.getPath("adminEditTrainer")).forward(req, resp);
        }

    }
}
