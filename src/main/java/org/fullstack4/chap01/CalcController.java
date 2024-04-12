package org.fullstack4.chap01;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name="/CalcController", urlPatterns="/calc")
public class CalcController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/input.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String num1 = req.getParameter("num1");
        String num2 = req.getParameter("num2");

        if (num1 == null || num2 == null || num1.isEmpty() || num2.isEmpty()) {
            req.setAttribute("errCode", "1");
            req.getRequestDispatcher("/WEB-INF/input.jsp").forward(req, resp);
        } else {
            req.setAttribute("errCode", "");
            resp.sendRedirect("/");
        }

    }
}