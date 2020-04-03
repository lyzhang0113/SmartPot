//package com.doby2333.IED.config;
//
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class LoginHandleInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if (request.getSession().getAttribute("id") == null) {
//            // not logged in
//            request.setAttribute("msg", "Please Login First Before Accessing This Content!");
//            request.getRequestDispatcher("/login.html").forward(request, response);
//            return false;
//        } else {
//            // logged in
//            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//            response.setHeader("Vary", "Origin");
//            response.setHeader("Access-Control-Allow-Credentials", "true");
//            response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
//            response.setHeader("Access-Control-Allow-Headers",
//                    "Origin, X-Requested-With, Content-Type, Accept, " + "X-CSRF-TOKEN");
//            return true;
//        }
//    }
//}
