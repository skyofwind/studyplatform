package org.studyplatform.filter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by dzj on 2017/6/12.
 */
public class LoginHandlerIntercepter implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String requestURI = httpServletRequest.getRequestURI();
        if(requestURI.indexOf("stuzone")>0||requestURI.indexOf("studentinfo")>0||requestURI.indexOf("coursemanage")>0||requestURI.indexOf("test")>0||requestURI.indexOf("updataPsw")>0||requestURI.indexOf("checkpassword")>0||requestURI.indexOf("starttest")>0||requestURI.indexOf("checkanswer")>0||requestURI.indexOf("checktest")>0||requestURI.indexOf("addcourse")>0){
            HttpSession session = httpServletRequest.getSession();
            String username=(String) session.getAttribute("username");
            if(username!=null){
                return true;
            }else {
                httpServletRequest.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(httpServletRequest,httpServletResponse);
                return false;
            }
        }else{
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
