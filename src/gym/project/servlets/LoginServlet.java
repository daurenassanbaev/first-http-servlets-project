package gym.project.servlets;

import gym.project.dto.UserDto;
import gym.project.service.UserService;
import gym.project.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final UserService userService = UserService.getINSTANCE();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("login")).forward(req, resp);
    }

    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.login(req.getParameter("email"), req.getParameter("password")).ifPresentOrElse(
                user -> onLoginSuccess(user, resp, req),
                () -> onLoginFail(req, resp)
                );
    }
    @SneakyThrows
    private void onLoginSuccess(UserDto userDto, HttpServletResponse resp, HttpServletRequest req) {
        req.getSession().setAttribute("user", userDto);
        if (userDto.getRole().equals("ADMIN")) {
            resp.sendRedirect("/admin");
        } else {
            resp.sendRedirect("/user");
        }
    }
    @SneakyThrows
    private void onLoginFail(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect("/login?error=email=" + req.getParameter("email"));
    }
}
