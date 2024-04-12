package org.fullstack4.chap01.controller;

import org.fullstack4.chap01.dto.MemberDTO;
import org.fullstack4.chap01.utils.CookieUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "/LogoutController", value = "/member/logout")
public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String user_id = String.valueOf(session.getAttribute("user_id"));
        String pwd = String.valueOf(session.getAttribute("pwd"));
        MemberDTO dto = (MemberDTO) session.getAttribute("member");

        //session.removeAttribute("user_id");
        //session.removeAttribute("pwd");
        //session.removeAttribute("member");
        session.invalidate();
        //CookieUtil.setDeleteCookie(resp, "", "/", 0, "user_id", user_id);
        CookieUtil.setDeleteCookie(resp, "", "/", 0, "auto_user_id", pwd);
        CookieUtil.setDeleteCookie(resp, "", "/", 0, "pwd", pwd);

        resp.sendRedirect("/member/login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
