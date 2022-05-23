package com.txg.www.until;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
@WebFilter("/*")
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
            //提交事务
            DataSourcePool.commitAndClose();
        } catch (Exception e) {
            //出现异常
            //回滚事务
            DataSourcePool.rollbackAndClose();
            e.printStackTrace();
            throw new RuntimeException(e);

        }
    }
    @Override
    public void destroy() {

    }
}
