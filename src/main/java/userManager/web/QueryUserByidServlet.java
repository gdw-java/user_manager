package userManager.web;


import userManager.domain.User;
import userManager.service.UserService;
import userManager.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*//根据id查询用户信息并回显到update.jsp*/
@WebServlet("/queryUserByid")
public class QueryUserByidServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int id =Integer.parseInt( request.getParameter("id"));
        UserService service= (UserService) new UserServiceImpl();
        User user=service.queryUserByid(id);
        request.setAttribute("userManager",user);
        request.getRequestDispatcher("/update.jsp").forward(request, response);

    }
}
