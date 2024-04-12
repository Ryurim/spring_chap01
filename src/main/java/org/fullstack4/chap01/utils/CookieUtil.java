package org.fullstack4.chap01.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
    public static void setCookies(HttpServletResponse resp, String domain, String path, int expire, String name, String val) {
        Cookie cookie = new Cookie(name, val);
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setMaxAge(expire);

        resp.addCookie(cookie);
    }

    public static String getCookieInfo(HttpServletRequest req,  String name) {
        String result = "";
        Cookie[] cookies = req.getCookies(); // 일단 쿠키를 다 갖고 와
        for (Cookie c : cookies) { // 그 안에서 순회 하면서
            if (name.equals(c.getName())) { // 내가 원하는 이름의 쿠키를 갖고와
                result = c.getValue(); // 그 쿠키의 값을 갖고와
            }
        }
        return result;
    }
    public static void setDeleteCookie(HttpServletResponse resp, String domain, String path, int expire, String name, String val) {
        setCookies(resp, domain, path, expire, name, val);
    }
}
