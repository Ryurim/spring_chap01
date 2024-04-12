package org.fullstack4.chap01.listener;


import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@Log4j2
@WebListener
public class F4Applistener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("============= init ==============");
        log.info("============= init ==============");
        log.info("============= init ==============");

        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("appName", "Fullstack4");

    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("============= destroyed ==============");
        log.info("============= destroyed ==============");
        log.info("============= destroyed ==============");
    }

}
