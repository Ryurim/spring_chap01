package org.fullstack4.chap01.controller;

import lombok.extern.log4j.Log4j2;
import org.fullstack4.chap01.dto.BbsDTO;
import org.fullstack4.chap01.service.BbsService;
import org.fullstack4.chap01.utils.Validation;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@Log4j2
@WebServlet(name = "/BbsModifyController", value = "/bbs/modify")
public class BbsModifyController extends HttpServlet {
    private BbsService service = BbsService.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idx = req.getParameter("idx") != null ? Integer.parseInt(req.getParameter("idx")) : 0;
        if (idx <= 0) {
            // 데이터베이스에 접근하기 전에 예외처리를 다 해줘야함!
        } else {
            try {
                // idx로 들어갔는데 글이 없으면 어떡할거야? -> 예외처리를 무조건 다 해줘야해!

                BbsDTO bbsDTO = service.view(idx);
                req.setAttribute("bbsDTO", bbsDTO);

            } catch (Exception e) {
                log.info("=================================================");
                log.info("수정 에러 : " + e.getMessage());
                log.info("=================================================");
                e.printStackTrace();
            }
            req.getRequestDispatcher("/WEB-INF/views/bbs/modify.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();

        Validation check = new Validation();
        boolean check_flag = true;

        int idx = req.getParameter("idx") != null ? Integer.parseInt(req.getParameter("idx")) : 0;

        String user_id = req.getParameter("user_id");



        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String display_date = req.getParameter("display_date");
        int read_cnt = 0;

        boolean titleCheck = check.checkLength(title,1, 10);
        boolean displayDateCheck = check.checkEmpty(display_date);
        boolean contentCheck = check.checkLength(content, 1, 200);

        if (!titleCheck) req.setAttribute("title_check_flag", "제목을 10자 이내로 입력하세요.");

        if (!displayDateCheck) req.setAttribute("displayDate_check_flag", "등록일을 선택하세요.");

        if (!contentCheck) req.setAttribute("content_check_flag", "내용을 200자 이내로 입력하세요.");




//        if (!titleCheck || !displayDateCheck || !contentCheck) {
//            check_flag = false;
//        }
        check_flag = titleCheck && displayDateCheck && contentCheck;

        if (check_flag) {
            BbsDTO bbsDTO = BbsDTO.builder()
                    .user_id(user_id)
                    .idx(idx)
                    .title(title)
                    .content(content)
                    .display_date(display_date)
                    .build();

            System.out.println("DTO : " + bbsDTO.toString());
            int result = 0;
            try {
                result = service.modify(bbsDTO);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (result > 0) {
                log.info("수정 완료");
                resp.sendRedirect("/bbs/list");
            } else {
                log.info("수정 오류!");
                req.setAttribute("result", "result = " + result + " 수정오류!");
                req.getRequestDispatcher("/WEB-INF/views/bbs/modify.jsp?idx=" + idx).forward(req, resp);
            }
        } else {
            System.out.println("입력 파라미터 체크");
            req.getRequestDispatcher("/WEB-INF/views/bbs/modify.jsp?idx=" + idx).forward(req, resp);
        }



    }
}
