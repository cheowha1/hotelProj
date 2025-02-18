package hotelproject.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
    
    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String token) {
        // 토큰을 이용해 현재 로그인한 사용자 정보 조회
        UserVo foundUser = userService.getUserByToken(token);

        if (foundUser == null) {
            return ResponseEntity.status(401).body("로그인이 필요합니다.");
        }

        // 사용자 정보 반환
        Map<String, Object> response = new HashMap<>();
        response.put("nickname", foundUser.getNickname());
        response.put("grade", foundUser.getGrade());
        response.put("point", foundUser.getPoint());

        return ResponseEntity.ok(response);
    }
    
}
