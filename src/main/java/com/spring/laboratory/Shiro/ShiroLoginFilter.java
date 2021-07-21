package com.spring.laboratory.Shiro;

import com.alibaba.fastjson.JSONObject;
import com.spring.laboratory.Entity.Result;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

//public class ShiroLoginFilter extends FormAuthenticationFilter {
//
//    /**
//     * 在访问controller前判断是否登录，返回json，不进行重定向。
//     * @param request
//     * @param response
//     * @return true-继续往下执行，false-该filter过滤器已经处理，不继续执行其他过滤器
//     * @throws Exception
//     */
//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
//        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//        if (!isAjax(request)) {
//            httpServletResponse.sendRedirect("/text/login");
//        } else {
//            //saveRequestAndRedirectToLogin(request, response);
//            /**
//             * @Mark 非ajax请求重定向为登录页面
//             */
//            httpServletResponse.setCharacterEncoding("UTF-8");
//            httpServletResponse.setContentType("application/json");
//            Result resultData = new Result();
//            resultData.setCode(403);
//            resultData.setMsg("登录认证失效，请重新登录!");
//            httpServletResponse.getWriter().write(JSONObject.toJSON(resultData).toString());
//        }
//        return false;
//    }
//
//    private boolean isAjax(ServletRequest request){
//        String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
//        if("XMLHttpRequest".equalsIgnoreCase(header)){
//            return Boolean.TRUE;
//        }
//        return Boolean.FALSE;
//    }
public class ShiroLoginFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
       ServletResponse servletResponse = (ServletResponse) response;

        PrintWriter out = null;
        HttpServletResponse res = (HttpServletResponse) response;
        try {
            res.setCharacterEncoding("UTF-8");
            res.setContentType("application/json");
            Result resultData = new Result();
            resultData.setCode(403);
            resultData.setMsg("登录认证失效，请重新登录!");
            servletResponse.getWriter().write(JSONObject.toJSON(resultData).toString());
        } catch (Exception e) {
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
        return false;
    }

    @Bean
    public FilterRegistrationBean registration(ShiroLoginFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }



}
