package hotelproject.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hotelproject.repositories.vo.UserVo;
import hotelproject.services.UserService;
import jakarta.servlet.http.HttpSession;

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
    public ResponseEntity<String> login(@RequestBody UserVo loginVo, HttpSession session) {
        UserVo foundUser = userService.authenticateUser(loginVo);
        if (foundUser == null) {
            return ResponseEntity.status(401).body("이메일 또는 비밀번호가 올바르지 않습니다.");
        }

        //  세션에 사용자 정보 저장
        session.setAttribute("loggedInUser", foundUser);

        return ResponseEntity.ok("로그인 성공");
    }
    
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate(); //  세션 무효화 (로그아웃)
        return ResponseEntity.ok("로그아웃 성공");
    }
    
    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo(HttpSession session) {
        String email = (String) session.getAttribute("userEmail");

        if (email == null) {
            return ResponseEntity.status(401).body("로그인이 필요합니다.");
        }

        UserVo user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }
    
    //	<포인트 기능 추가>
    // 포인트 사용 내역 조회 API
    @GetMapping("/{userNo}/point-history")
    public ResponseEntity<?> getUserPointHistory(@PathVariable int userNo) {
        Map<String, Object> history = userService.getUserPointHistory(userNo);
        if (history == null || history.isEmpty()) {
            return ResponseEntity.badRequest().body("포인트 내역이 없습니다.");
        }
        return ResponseEntity.ok(history);
    }

    // 결제 시 포인트 적립 API
    @PostMapping("/{userNo}/earn-points")
    public ResponseEntity<String> addPointsAfterPayment(@PathVariable int userNo, @RequestParam int amount) {
        userService.addPointsAfterPayment(userNo, amount);
        return ResponseEntity.ok("포인트가 적립되었습니다.");
    }

    // 마이페이지에서 포인트 충전 API
    @PostMapping("/{userNo}/charge-points")
    public ResponseEntity<String> chargePoints(@PathVariable int userNo, @RequestParam int amount) {
        boolean success = userService.chargePoint(userNo, amount);
        if (success) {
            return ResponseEntity.ok("포인트 충전 성공");
        }
        return ResponseEntity.status(400).body("포인트 충전 실패");
    }
    
    

}
