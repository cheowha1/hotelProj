package hotelproject.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SessionInterceptor implements HandlerInterceptor {
	 
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 로그인 & 회원가입 API는 세션 체크 예외 처리
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/users/login") || requestURI.startsWith("/users/register")) {
            return true;
        }

        // 세션에서 userId 확인 (로그인 여부 체크)
        Object userId = request.getSession().getAttribute("userId");
        if (userId == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized 반환
            response.getWriter().write("로그인이 필요합니다.");
            return false;
        }

        return true; // 로그인된 상태면 정상 처리
    }
	
}