package hotelproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hotelproject.repositories.vo.UserVo;
import hotelproject.services.UserService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
public class UserController {
	
	  @Autowired
	    private UserService userService;

	    @PostMapping("/register")
	    public boolean registerUser(@RequestBody UserVo user) {
	        return userService.registerUser(user);
	    }
  
	    @PostMapping("/login")
	    public UserVo loginUser(@RequestParam String id, @RequestParam String password, HttpSession session) {
	        UserVo user = userService.loginUser(id, password);
	        if (user == null) {
	            throw new IllegalArgumentException("로그인 실패: 아이디 또는 비밀번호가 올바르지 않습니다.");
	        }
	        session.setAttribute("userNo", user.getNo()); // 세션 저장
	        return user;
	    }

	    @PostMapping("/logout")
	    public String logoutUser(HttpSession session) {
	        session.invalidate();
	        return "로그아웃 완료";
	    }
}
