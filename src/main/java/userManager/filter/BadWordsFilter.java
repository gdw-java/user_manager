package userManager.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

/**
 * 脏词过滤
 */
@WebFilter(urlPatterns = "/ff")
public class BadWordsFilter implements Filter {
    ArrayList<String> badwords=new ArrayList<>();
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //your code
        String username = request.getParameter("username");
        System.out.println(username);
        HttpServletRequest proxyRequest = (HttpServletRequest) Proxy.newProxyInstance(request.getClass().getClassLoader(), request.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("getParameter")) {
                    for (String badword : badwords) {
                        if (username!=null&&username.contains(badword)) {
                            String replace = username.replace(badword, "***");
                            return replace;
                        }
                    }
                }
                return method.invoke(request, args);
            }
        });

        chain.doFilter(proxyRequest, response);
    }

    public void init(FilterConfig config) throws ServletException {
        String realPath = config.getServletContext().getRealPath("/badwords.txt");
        System.out.println(realPath);
        try {
            InputStreamReader isr=new InputStreamReader(new FileInputStream(realPath),"utf8");
            BufferedReader bd=new BufferedReader(isr);
            String line=null;
            while ((line = bd.readLine()) != null) {
                badwords.add(line);
            }
            System.out.println(badwords);
        } catch (IOException e1) {
            e1.printStackTrace();
        }


    }

}
