package org.fullstack4.chap01.filter;


import lombok.extern.log4j.Log4j2;
import org.fullstack4.chap01.dto.MemberDTO;
import org.fullstack4.chap01.service.MemberService;
import org.fullstack4.chap01.utils.CookieUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
@WebFilter(urlPatterns = {"/bbs/*", "/event/*", "/member/my/*"})
public class LoginCheckFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("Login Check Filter");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        String user_id = CookieUtil.getCookieInfo(req, "auto_user_id");
        String pwd = CookieUtil.getCookieInfo(req, "pwd");


        System.out.println(user_id);

        if (user_id != null && user_id != "" && pwd != "" && pwd != null) {
            try {
                MemberDTO dto = MemberService.INSTANCE.login(user_id, pwd);
                //session.setAttribute("loginInfo", dto);
                session.setAttribute("member", dto);
                session.setAttribute("loginInfo", dto);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (session.getAttribute("loginInfo") == null) {
            resp.sendRedirect("/member/login");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
