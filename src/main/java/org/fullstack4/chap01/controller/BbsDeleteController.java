package org.fullstack4.chap01.controller;

import lombok.extern.log4j.Log4j2;
import org.fullstack4.chap01.service.BbsService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@Log4j2
@WebServlet(name = "/BbsDeleteController", value = "/bbs/delete")
public class BbsDeleteController extends HttpServlet {

    private BbsService service = BbsService.INSTANCE;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idx = req.getParameter("idx") != null ? Integer.parseInt(req.getParameter("idx")) : 0;
        System.out.println("삭제 실패 : GET방식으로 접근, idx = " + idx);
        resp.sendRedirect("/bbs/view?idx="+idx);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int idx = req.getParameter("idx") != null ? Integer.parseInt(req.getParameter("idx")) : 0;
//        if (idx > 0) {
//
//            System.out.println("삭제완료 : POST, idx = " + idx);
//            resp.sendRedirect("/bbs/list");
//        } else {
//            System.out.println("삭제실패 : POST");
//            resp.sendRedirect("/bbs/view");
//        }
        String idx = req.getParameter("idx");
        int result = 0;

        if (idx != null && Integer.parseInt(idx) > 0) {
            try {
                result = service.delete(Integer.parseInt(idx));

                log.info("===================");
                log.info("idx : " + idx);
                log.info("result : " + result);
                log.info("===================");

                resp.sendRedirect("/bbs/list");

//                if (result >= 0) { 에러 정책이 없으면? 없애버려
//                    resp.sendRedirect("/bbs/list");
//                } else {
//                    //에러 띄워주는 페이지로 이동
//                    //디스패처 이용
//                }
            } catch (Exception e) {
                log.info("===================");
                log.info("error : " + e.getMessage());
                log.info("===================");
                e.printStackTrace();
            }
        } else {
            log.info("===================");
            log.info("error - idx : " + idx);
            log.info("===================");
        }

    }
}
