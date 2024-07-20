package gym.project.servlets;

import gym.project.dto.UserDto;
import gym.project.entity.User;
import gym.project.service.AdminService;
import gym.project.service.UserService;
import gym.project.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(name = "AdminUserManagement", urlPatterns = "/adminUserManagement")
public class AdminUserManagementServlet extends HttpServlet {
    private final UserService userService = UserService.getINSTANCE();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = ((UserDto) req.getSession().getAttribute("user")).getId();
        req.setAttribute("users", userService.getAllUsersExceptId(id));
        req.getRequestDispatcher(JspHelper.getPath("adminUserManagement")).forward(req, resp);
    }
}
