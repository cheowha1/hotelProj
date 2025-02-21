package hotelproject.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hotelproject.repositories.vo.PointVo;
import hotelproject.repositories.vo.ReservationVo;
import hotelproject.repositories.vo.ReviewVo;
import hotelproject.repositories.vo.UserVo;
import hotelproject.services.UserService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
	
    private UserService userService;

    // 회원가입 (회원가입 시 포인트 1000 지급 + 추천인 3000 지급)
    @PostMapping("/register")
    public String registerUser(@RequestBody UserVo user) {
        boolean success = userService.registerUser(user);
        return success ? "회원가입 성공 (포인트 지급 완료)" : "회원가입 실패";
    }

    // 로그인 (세션 저장)
    @PostMapping("/login")
    public UserVo loginUser(@RequestParam String id, @RequestParam String password, HttpSession session) {
        UserVo user = userService.loginUser(id, password);
        if (user != null) {
            session.setAttribute("userNo", user.getNo());
            return user;
        }
        return null;
    }

    // 로그아웃 (세션 삭제)
    @PostMapping("/logout")
    public String logoutUser(HttpSession session) {
        userService.logoutUser();
        return "로그아웃 성공";
    }

    // 유저 정보 조회
    @GetMapping("/info")
    public UserVo getUserInfo(HttpSession session) {
        Integer userNo = (Integer) session.getAttribute("userNo");
        if (userNo == null) {
            return null;
        }
        return userService.getUserInfo(userNo);
    }

    // 유저의 포인트 내역 조회 (PointService와 연동)
    @GetMapping("/points/history")
    public List<PointVo> getUserPointHistory(HttpSession session) {
        Integer userNo = (Integer) session.getAttribute("userNo");
        if (userNo == null) {
            return null;
        }
        return userService.getUserPointHistory(userNo);
    }

    // 유저 포인트 충전 (PointService와 연동)
    @PostMapping("/points/charge")
    public String chargeUserPoints(@RequestParam int amount, HttpSession session) {
        Integer userNo = (Integer) session.getAttribute("userNo");
        if (userNo == null) {
            return "세션이 만료되었습니다. 다시 로그인해주세요.";
        }
        boolean success = userService.chargeUserPoints(userNo, amount);
        return success ? "포인트 충전 성공" : "포인트 충전 실패";
    }
    
    // 유저 리뷰 조회
    @GetMapping("/reviews")
    public List<ReviewVo> getUserReviews(HttpSession session) {
        Integer userNo = (Integer) session.getAttribute("userNo");
        if (userNo == null) {
            return null;
        }
        return userService.getUserReviews(userNo);
    }
    
    // 로그인한 유저의 모든 데이터 조회 (유저 정보 + 포인트 내역 + 리뷰 + 예약 내역)
    @GetMapping("/dashboard")
    public UserDashboardResponse getUserDashboard(HttpSession session) {
        Integer userNo = (Integer) session.getAttribute("userNo");
        if (userNo == null) {
            return null;
        }
        UserVo user = userService.getUserInfo(userNo);
        List<PointVo> points = userService.getUserPointHistory(userNo);
        List<ReviewVo> reviews = userService.getUserReviews(userNo);
        List<ReservationVo> reservations = userService.getUserReservations(userNo);
        return new UserDashboardResponse(user, points, reviews, reservations);
    }

    // 내부 DTO 클래스 (유저 대시보드 데이터 반환용)
    public static class UserDashboardResponse {
        private UserVo user;
        private List<PointVo> points;
        private List<ReviewVo> reviews;
        private List<ReservationVo> reservations;

        public UserDashboardResponse(UserVo user, List<PointVo> points, List<ReviewVo> reviews, List<ReservationVo> reservations) {
            this.user = user;
            this.points = points;
            this.reviews = reviews;
            this.reservations = reservations;
        }

        public UserVo getUser() { return user; }
        public List<PointVo> getPoints() { return points; }
        public List<ReviewVo> getReviews() { return reviews; }
        public List<ReservationVo> getReservations() { return reservations; }
    }
}
