package org.fullstack4.chap01.controller;

import lombok.extern.log4j.Log4j2;
import org.fullstack4.chap01.dto.MemberDTO;
import org.fullstack4.chap01.service.MemberService;
import org.fullstack4.chap01.utils.CookieUtil;
import org.fullstack4.chap01.utils.Validation;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@Log4j2

@WebServlet(name = "/LoginController", value = "/member/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            req.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("=========================");
        log.info("/member/login");
        log.info("=========================");

        Validation check = new Validation();

        String user_id = req.getParameter("user_id");
        String pwd = req.getParameter("pwd");
        String idSave = req.getParameter("idSave") != null ? "Y" : "N";
        String autoLogin = req.getParameter("autoLogin") != null ? "Y" : "N";

        boolean check_flag = true;

        boolean idCheck = check.checkEmpty(user_id);
        boolean pwdCheck = check.checkEmpty(pwd);

        check_flag = idCheck && pwdCheck;




        HttpSession session = req.getSession();

        try {

            if (check_flag) {
                System.out.println("id : " + user_id + ", pwd : " + pwd);
                MemberDTO dto = MemberService.INSTANCE.login(user_id, pwd);
                if (dto != null) {
                    log.info("loginController - 로그인 성공");
                    session.setAttribute("member", dto);
                    session.setAttribute("loginInfo", dto);
                    if (idSave.equals("Y")) {
//                        session.setAttribute("user_id", user_id);
//                        session.setAttribute("idS", "checked");
                        CookieUtil.setCookies(resp, "", "/", 60*60*24, "user_id", user_id);
                        CookieUtil.setCookies(resp, "", "/", 60*60*24, "idS", "checked");
                    }
                    if (autoLogin.equals("Y")) {
                        session.setAttribute("user_id", user_id);
                        session.setAttribute("pwd", pwd);
                        CookieUtil.setCookies(resp, "", "/", 60*60*24, "auto_user_id", user_id);
                        CookieUtil.setCookies(resp, "", "/", 60*60*24, "pwd", pwd);

                    }
                    if (idSave.equals("N")) {
                        CookieUtil.setDeleteCookie(resp, "", "/", 0, "user_id", "");
                        CookieUtil.setDeleteCookie(resp, "", "/", 0, "idS", "");

                    }
                    if (autoLogin.equals("N")) {
                        CookieUtil.setDeleteCookie(resp, "", "/", 0, "auto_user_id", user_id);
                        CookieUtil.setDeleteCookie(resp, "", "/", 0, "pwd", pwd);
                    }
                    resp.sendRedirect("/bbs/list");
                }
                else {
                    log.info("loginController - 로그인 오류");
                    session.removeAttribute("member");
                    req.setAttribute("loginError", "block");
                    req.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(req, resp);
                }
            }
            else {
                System.out.println("id : " + user_id + ", pwd : " + pwd);
                log.info("loginController - 아이디/비밀번호 비어있음");
                req.setAttribute("isEmpty", "block");
                req.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(req, resp);
            }

        } catch(Exception e) {
            System.out.println("컨트롤러 - 로그인 오류");
            req.setAttribute("loginError", "block");
            e.printStackTrace();
        }


    }
}
