package hotelproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
  
	    // 로그인
	    @PostMapping("/login")
	    public ResponseEntity<?> loginUser(@RequestBody UserVo user) {
	        try {
	            UserVo loggedInUser = userService.loginUser(user.getId(), user.getPassword());
	            return ResponseEntity.ok(loggedInUser);
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.status(401).body(e.getMessage());
	        }
	    }

	    @PostMapping("/logout")
	    public String logoutUser(HttpSession session) {
	        session.invalidate();
	        return "로그아웃 완료";
	    }
}