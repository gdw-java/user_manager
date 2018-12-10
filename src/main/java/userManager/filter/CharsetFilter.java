package userManager.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CharsetFilter",urlPatterns = "/*")
public class CharsetFilter implements Filter {
    public void destroy() {
    }
    //乱码处理
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request= (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String method = request.getMethod();
        if ("POST".equalsIgnoreCase(method)) {
        request.setCharacterEncoding("utf-8");
        }
        response.setContentType("text/html;charset=utf-8");
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
