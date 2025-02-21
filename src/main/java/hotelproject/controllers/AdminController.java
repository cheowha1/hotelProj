package hotelproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hotelproject.repositories.vo.UserVo;
import hotelproject.services.AdminService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin")
public class AdminController {
	

    @Autowired
    private AdminService adminService;

    // 유저 목록 조회
    @GetMapping("/users")
    public List<UserVo> getAllUsers(HttpSession session) {
        return adminService.getAllUsers();
    }

    // 유저 정보 수정
    @PutMapping("/users/{id}")
    public String updateUser(@PathVariable int id, @RequestBody UserVo user) {
        boolean success = adminService.updateUser(id, user);
        return success ? "유저 정보 수정 성공" : "유저 정보 수정 실패";
    }

    // 유저 포인트 지급
    @PostMapping("/users/{id}/points/give")
    public String giveUserPoints(@PathVariable int id, @RequestParam int amount) {
        boolean success = adminService.giveUserPoints(id, amount);
        return success ? "포인트 지급 성공" : "포인트 지급 실패";
    }

    // 유저 포인트 차감
    @PostMapping("/users/{id}/points/deduct")
    public String deductUserPoints(@PathVariable int id, @RequestParam int amount) {
        boolean success = adminService.deductUserPoints(id, amount);
        return success ? "포인트 차감 성공" : "포인트 차감 실패";
    }

    // 유저 등급 변경
    @PutMapping("/users/{id}/grade")
    public String changeUserGrade(@PathVariable int id, @RequestParam int grade) {
        boolean success = adminService.updateUserGrade(id, grade);
        return success ? "유저 등급 변경 성공" : "유저 등급 변경 실패";
    }
}

