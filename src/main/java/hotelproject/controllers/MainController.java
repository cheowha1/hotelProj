package hotelproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import hotelproject.repositories.vo.UserVo;
import hotelproject.services.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/main")
    public String mainPage(HttpSession session, Model model) {
        // 현재 로그인한 사용자 이메일 가져오기 (세션 방식)
        String email = (String) session.getAttribute("userEmail");

        if (email != null) {
            // 이메일로 사용자 정보 조회
            UserVo user = userService.getUserByEmail(email);
            model.addAttribute("nickname", user.getNickname());
            model.addAttribute("grade", user.getGrade());
            model.addAttribute("point", user.getPoint());

            // Admin이면 관리자 페이지 링크 추가
            if ("admin".equals(user.getGrade())) {
                model.addAttribute("isAdmin", true);
            } else {
                model.addAttribute("isAdmin", false);
            }
        } else {
            model.addAttribute("nickname", "로그인이 필요합니다.");
            model.addAttribute("grade", "");
            model.addAttribute("point", 0);
            model.addAttribute("isAdmin", false);
        }

        return "main"; // main.jsp 또는 main.html로 이동
    }
}
