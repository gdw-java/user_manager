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

//添加用户
@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        User user = CommonsBean.populate(User.class, request);
        UserService service = new UserServiceImpl();
        boolean result = service.addUser(user);
        if (result) {
            request.setAttribute("msg", "邮箱已存在");
            request.getRequestDispatcher("/add.jsp").forward(request, response);
        } else {
            response.sendRedirect("add_ok.jsp");
        }

    }
}
