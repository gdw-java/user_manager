package userManager.web;


import userManager.domain.PageBean;
import userManager.service.UserService;
import userManager.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/pageUser")
public class PageUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String curPageStr = request.getParameter("curPage");
        int curPage=0;
        int pageSize=3;
        if (curPageStr == null) {
            curPage = 1;
        } else {
            curPage=Integer.parseInt(curPageStr);
        }
        UserService service= (UserService) new UserServiceImpl();
        PageBean pageBean=service.getPageBean(curPage,pageSize);
        request.setAttribute("pb",pageBean);
        request.getRequestDispatcher("/page.jsp").forward(request, response);
    }
}
