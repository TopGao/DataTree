package com.jira.Interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor implements HandlerInterceptor {
    Logger logger = Logger.getLogger(MyInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // TODO Auto-generated method stub
        logger.info("------preHandle------");
        //获取session
        //HttpSession session = request.getSession(true);
        //判断用户ID是否存在，不存在就跳转到登录界面
        if(request.getSession().getAttribute("account") != "cdcadmin"){
            logger.info("------:跳转到login页面！");
            response.sendRedirect(request.getContextPath()+"/cdc/myTest");
            System.out.println(request.getContextPath().toString()+"/cdc/myTest");
            //RequestDispatcher rd = request.getRequestDispatcher("/myTest");
            //rd.forward(request, response);
            //提交至http:localhost:8080/Test/ooo
            return false;
        }else{
            //session.setAttribute("userId", session.getAttribute("userId"));
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // >>>MyInterceptor1>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
    	 System.out.println(">>>MyInterceptor1>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        //>>>MyInterceptor1>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行(主要是用于进行资源清理工作)
    	System.out.println(">>>MyInterceptor1>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }

}
