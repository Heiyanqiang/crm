package com.bjpowernode.crm.web.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    //过滤器
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        System.out.println("进入到过滤字符编码的过滤器");
        //过滤post请求中文参数乱码
        req.setCharacterEncoding("utf-8");
        //过滤器响应中文乱码
        resp.setContentType("text/html;charset=utf-8");
        //将请求行放行
        chain.doFilter(req,resp);
    }
}
