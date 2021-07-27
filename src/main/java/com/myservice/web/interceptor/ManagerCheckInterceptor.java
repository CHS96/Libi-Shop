package com.myservice.web.interceptor;

import com.myservice.domain.member.Grade;
import com.myservice.domain.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class ManagerCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);

        Member loginMember = (Member) session.getAttribute("loginMember");
        if (loginMember.getGrade() != Grade.MANAGER) {
            log.info("관리자가 아닌 사용자 요청");
            response.sendRedirect("/");
        }
        return true;
    }
}
