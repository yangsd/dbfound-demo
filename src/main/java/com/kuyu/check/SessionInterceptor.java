package com.kuyu.check;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kuyu.util.PropertyHelper;
import com.nfwork.dbfound.core.Context;
import com.nfwork.dbfound.dto.ResponseObject;
import com.nfwork.dbfound.model.ModelEngine;


public class SessionInterceptor implements Filter {

    private static final String loginout = PropertyHelper.getValue("login.url");
    
    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        final HttpSession session = request.getSession();

        Object user_id = session.getAttribute("user_id");

        String requestUri = request.getRequestURI();
        if(notFilterService(requestUri)){
        	 chain.doFilter(request, response);
        }else {
        	 chain.doFilter(request, response);
//            request.getRequestDispatcher("/noPower.jsp").forward(
//                    request, response);
        }
    }
    
    @Override
    public void init(final FilterConfig arg0) throws ServletException {
    }

    // 不用过滤的
    public static boolean notFilterService(final String requestPath) {
        final List<String> passFilter = new ArrayList<String>();
        passFilter.add("/interface/");
        passFilter.add("/js/");
        passFilter.add("/DBFoundUI/");
        passFilter.add("/css/");
        passFilter.add("/images/");
        passFilter.add("/index.jsp");
        passFilter.add("/login.jsp");
        passFilter.add("/loginWindow.jsp");
        passFilter.add("/code.jsp");
        passFilter.add("integral/loginAction.do!login");

        for (final String str : passFilter) {
            if (requestPath.contains(str)) {
                return true;
            }
        }
        return false;
    }
}
