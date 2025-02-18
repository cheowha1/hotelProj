package hotelproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hotelproject.repositories.vo.UserVo;
import hotelproject.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    // 회원가입 엔드포인트
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody UserVo userVo) {
        return userService.registerUser(userVo);
    }
    
    // 로그인 엔드포인트
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserVo userVo) {
        return userService.authenticateUser(userVo);
    }
    
    // 로그인 엔드포인트
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserVo loginVo) {
        return userService.authenticateUser(loginVo);
    }
}
