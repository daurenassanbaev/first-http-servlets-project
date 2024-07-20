package gym.project.servlets;

import gym.project.dto.UserDto;
import gym.project.exception.EmailValidationException;
import gym.project.service.UserService;
import gym.project.util.JspHelper;
import gym.project.entity.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private static final UserService userService = UserService.getINSTANCE();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", Role.values());
        req.getRequestDispatcher(JspHelper.getPath("registration")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto userDto = UserDto.builder()
                .username(req.getParameter("name"))
                .password(req.getParameter("password"))
                .email(req.getParameter("email"))
                .role(req.getParameter("role"))
                .build();
        try {
            userService.create(userDto);
            resp.sendRedirect("/login");
        } catch (EmailValidationException e) {
            req.setAttribute("error", e.getMessage());
            doGet(req, resp);
        }
    }
}
