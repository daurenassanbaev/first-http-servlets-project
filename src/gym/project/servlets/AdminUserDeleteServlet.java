package gym.project.servlets;

import gym.project.service.AdminService;
import gym.project.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/adminUserManagement/adminDeleteUser")
public class AdminUserDeleteServlet extends HttpServlet {
    private final UserService userService = UserService.getINSTANCE();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("userId"));
        Integer id2 = userService.deleteUser(id);
        if (id2 >= 1) {
            resp.sendRedirect("/adminUserManagement");
        }
    }
}
