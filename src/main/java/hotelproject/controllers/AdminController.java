package hotelproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hotelproject.repositories.vo.UserVo;
import hotelproject.services.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	// 모든 회원 목록 조회
	@GetMapping("/users")
	public ResponseEntity<List<UserVo>> getAllUsers() {
		return ResponseEntity.ok(adminService.getAllUsers());
		
	}
	
	// 특정 회원 등급 변경
	@PutMapping("/users/{id}/grade")
	public ResponseEntity<String> updateUserGrade(@PathVariable int id, @RequestParam String grade) {
		adminService.updateUserGrade(id, grade);
		return ResponseEntity.ok("회원 등급이 변경되었습니다.");
	}
	
    // 회원 포인트 지급
    @PostMapping("/points/charge")
    public ResponseEntity<String> chargePoint(@RequestParam int userId, @RequestParam int point) {
        adminService.chargePoint(userId, point);
        return ResponseEntity.ok("포인트가 지급되었습니다.");
    }

    // 회원 포인트 차감
    @PostMapping("/points/deduct")
    public ResponseEntity<String> deductPoint(@RequestParam int userId, @RequestParam int point) {
        adminService.deductPoint(userId, point);
        return ResponseEntity.ok("포인트가 차감되었습니다.");
    }

    // 포인트 정책 수정 (기본 지급 포인트 변경)
    @PutMapping("/points/settings")
    public ResponseEntity<String> updatePointSettings(@RequestParam int defaultPoint) {
        adminService.updatePointSettings(defaultPoint);
        return ResponseEntity.ok("포인트 정책이 변경되었습니다.");
    }
}

