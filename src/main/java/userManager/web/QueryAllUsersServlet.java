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
import java.util.List;


//查询所有用户
@WebServlet("/queryAllUser")
public class QueryAllUsersServlet extends HttpServlet {
   private UserService service= (UserService) new UserServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<User> users=service.queryAllUsers();
        request.setAttribute("users",users);
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
