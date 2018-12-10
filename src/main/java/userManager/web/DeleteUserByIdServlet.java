package userManager.web;



import userManager.service.UserService;
import userManager.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//根据id删除用户 逻辑删除
@WebServlet("/deleteUserById")
public class DeleteUserByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        UserService service= (UserService) new UserServiceImpl();
        service.deleteUserById(id);
        response.sendRedirect("/delete_ok.jsp");
    }
}
