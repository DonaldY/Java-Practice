package com.donaldy.concurrency.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response) {
        log.info("preHandle");
        return true;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response) {
        RequestHolder.remove();
        log.info("afterCompletion");
        return;
    }
}
