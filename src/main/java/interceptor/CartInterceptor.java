package interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {

        if(request.getSession().getAttribute("sessionUser")!=null)
            return true;
        request.setAttribute("code", "error");//为了显示X图片
        request.setAttribute("msg", "您还没有登录，不能访问本资源");
        request.getRequestDispatcher("/jsps/msg.jsp").forward(request, response);
        return false;
    }



}
