ackage hotelproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hotelproject.repositories.vo.HotelVo;
import hotelproject.repositories.vo.ReviewVo;
import hotelproject.repositories.vo.UserVo;
import hotelproject.services.HotelService;
import hotelproject.services.ReviewService;
import hotelproject.services.UserService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private UserService userService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private ReviewService reviewService;

    // 메인 대시보드 데이터 반환 (유저 정보 + 추천 호텔 + 최신 리뷰)
    @GetMapping("")
    public DashboardResponse getDashboardData(HttpSession session) {
        Integer userNo = (Integer) session.getAttribute("userNo");
        UserVo user = (userNo != null) ? userService.getUserInfo(userNo) : null;
        List<HotelVo> recommendedHotels = hotelService.getRecommendedHotels();
        List<ReviewVo> latestReviews = reviewService.getLatestReviews();
        return new DashboardResponse(user, recommendedHotels, latestReviews);
    }

    // 내부 DTO 클래스 (대시보드 데이터 반환용)
    public static class DashboardResponse {
        private UserVo user;
        private List<HotelVo> recommendedHotels;
        private List<ReviewVo> latestReviews;

        public DashboardResponse(UserVo user, List<HotelVo> recommendedHotels, List<ReviewVo> latestReviews) {
            this.user = user;
            this.recommendedHotels = recommendedHotels;
            this.latestReviews = latestReviews;
        }

        public UserVo getUser() { return user; }
        public List<HotelVo> getRecommendedHotels() { return recommendedHotels; }
        public List<ReviewVo> getLatestReviews() { return latestReviews; }
    }
}