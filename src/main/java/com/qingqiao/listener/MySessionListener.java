package com.qingqiao.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class MySessionListener implements HttpSessionListener {

    private Integer number = 0;

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        ServletContext application = httpSessionEvent.getSession().getServletContext();
        Object count = application.getAttribute("count");
        if(count instanceof Integer){
            number = (Integer) count;
        }
        number++;
        application.setAttribute("count",number);
        System.out.println(application.getAttribute("count"));
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        ServletContext application = httpSessionEvent.getSession().getServletContext();
        Object count = application.getAttribute("count");
        if(count instanceof Integer){
            number = (Integer) count;
        }
        number--;
        application.setAttribute("count",number);
        System.out.println(application.getAttribute("count"));
    }
}


