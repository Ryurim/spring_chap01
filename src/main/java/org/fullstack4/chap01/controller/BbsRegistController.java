package org.fullstack4.chap01.controller;

import org.fullstack4.chap01.dto.BbsDTO;
import org.fullstack4.chap01.service.BbsService;
import org.fullstack4.chap01.utils.Validation;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "/BbsRegistController", value = "/bbs/regist")
public class BbsRegistController extends HttpServlet {
    private BbsService service = BbsService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/bbs/regist.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Validation check = new Validation();
        boolean check_flag = true;

//        String title = req.getParameter("title");
//        String reg_date = req.getParameter("reg_date");
//        String content = req.getParameter("content");
//        String[] hobby = req.getParameterValues("hobby");
//        String sex = req.getParameter("sex");

        String user_id = req.getParameter("user_id");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String display_date = req.getParameter("display_date");
        int read_cnt = 0;

        boolean titleCheck = check.checkLength(title,1, 10);
        boolean displayDateCheck = check.checkEmpty(display_date);
        boolean contentCheck = check.checkLength(content, 1, 200);

        //if (!titleCheck) req.setAttribute("title_check_flag", "제목을 10자 이내로 입력하세요.");
        req.setAttribute("title_check_flag", (titleCheck ? "none" : "block"));
        System.out.println(titleCheck);

        if (!displayDateCheck) req.setAttribute("displayDate_check_flag", "등록일을 선택하세요.");

        if (!contentCheck) req.setAttribute("content_check_flag", "내용을 200자 이내로 입력하세요.");

//        if (!check.checkArray(hobby)) req.setAttribute("hobby_check_flag", "취미를 선택하세요.");
//
//        if (!check.checkEmpty(sex)) req.setAttribute("sex_check_flag", "성별을 선택하세요.");

//        resp.setContentType("text/html; charset=UTF-8");
//        PrintWriter out = resp.getWriter();
//        out.println("<html>");
//        out.println("<body>");
//        out.println("<br>title : " + title);
//        out.println("<br>reg_date : " + reg_date);
//        out.println("<br>content : " + content);
//        out.println("<br>hobby : " + Arrays.toString(hobby));
//        out.println("<br>hobby : ");
//        for (int i=0; i<hobby.length; i++) {
//            out.println(hobby[i] + ", ");
//        }
//        out.println("<br>sex : " + sex);
//
//        out.println("</body>");
//        out.println("</html>");

//
//        if (!check.checkLength(title,1, 10) || !check.checkEmpty(reg_date) || !check.checkLength(content, 1, 200) || !check.checkArray(hobby) || !check.checkEmpty(sex)) {
//            check_flag = false;
//        }

//        if (check_flag) {
//            System.out.println("등록완료");
//            System.out.println(title + ", " + reg_date + ", " + content + ", " + Arrays.toString(hobby) + ", " + sex);
//            resp.sendRedirect("/bbs/list");
//        }
//        else {
//            System.out.println("등록실패");
//            req.getRequestDispatcher("/WEB-INF/views/bbs/regist.jsp").forward(req, resp);
//        }



//        if (!titleCheck || !displayDateCheck || !contentCheck) {
//            check_flag = false;
//        }
        check_flag = titleCheck && displayDateCheck && contentCheck;

        if (check_flag) {
            BbsDTO bbsDTO = BbsDTO.builder()
                    .user_id(user_id)
                    .title(title)
                    .content(content)
                    .display_date(display_date)
                    .read_cnt(read_cnt)
                    .build();

            System.out.println("DTO : " + bbsDTO.toString());
            int result = 0;
            try {
                result = service.regist(bbsDTO);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (result > 0) {
                resp.sendRedirect("/bbs/list");
            } else {
                System.out.println("등록 루틴 문제 발생");
                req.setAttribute("result", "result = " + result + " 등록오류!");
                req.getRequestDispatcher("/WEB-INF/views/bbs/regist.jsp").forward(req, resp);
            }
        } else {
            System.out.println("입력 파라미터 체크");
            req.getRequestDispatcher("/WEB-INF/views/bbs/regist.jsp").forward(req, resp);
        }


    }
}
