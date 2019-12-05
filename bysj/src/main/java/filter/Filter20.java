package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebFilter(filterName = "Filter20", urlPatterns = {"/*"})
public class Filter20 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filter 2 - encoding begins");
        HttpServletRequest request = (HttpServletRequest) req;
        String path = request.getRequestURI();
        SimpleDateFormat date = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");//设置日期格式
        String start = date.format(new Date());// new Date()为获取当前系统时间
        System.out.println("请求的资源为：" + path + "@" + start);
        filterChain.doFilter(req, resp);//执行其他过滤器，如过滤器已经执行完毕，则执行原请求
        System.out.println("Filter 2 - encoding ends");
    }
}