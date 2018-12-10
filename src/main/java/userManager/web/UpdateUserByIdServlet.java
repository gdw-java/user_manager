package userManager.web;

import userManager.domain.User;
import userManager.service.UserService;
import userManager.service.impl.UserServiceImpl;
import userManager.utils.CommonsBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateUser")
public class UpdateUserByIdServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        User user = CommonsBean.populate(User.class, request);
        UserService service = new UserServiceImpl();
        service.updateUserById(user);
        response.sendRedirect("/update_ok.jsp");

    }
}
