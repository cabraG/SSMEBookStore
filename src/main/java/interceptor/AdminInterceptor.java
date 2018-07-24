package interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {

        if(request.getSession().getAttribute("admin")!=null)
            return true;
        request.setAttribute("msg", "您还没有登录，不要瞎遛达！");
        request.getRequestDispatcher("/adminjsps/login.jsp").forward(request,response);
        return false;
    }



}
