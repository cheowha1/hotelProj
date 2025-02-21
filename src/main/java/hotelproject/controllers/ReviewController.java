package hotelproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hotelproject.repositories.vo.ReviewVo;
import hotelproject.services.ReviewService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

	 @Autowired
	    private ReviewService reviewService;

	    // 리뷰 추가
	    @PostMapping("/add")
	    public String addReview(@RequestBody ReviewVo review, HttpSession session) {
	        Integer userNo = (Integer) session.getAttribute("userNo");
	        if (userNo == null) {
	            return "로그인이 필요합니다.";
	        }
	        review.setUserNo(userNo);
	        boolean success = reviewService.addReview(review);
	        return success ? "리뷰 작성 성공" : "리뷰 작성 실패";
	    }

	    // 특정 호텔의 리뷰 조회
	    @GetMapping("/hotel/{hotelNo}")
	    public List<ReviewVo> getReviewsByHotel(@PathVariable int hotelNo) {
	        return reviewService.getReviewsByHotel(hotelNo);
	    }

	    // 특정 유저의 리뷰 조회
	    @GetMapping("/user")
	    public List<ReviewVo> getReviewsByUser(HttpSession session) {
	        Integer userNo = (Integer) session.getAttribute("userNo");
	        if (userNo == null) {
	            return null;
	        }
	        return reviewService.getReviewsByUser(userNo);
	    }

	    // 리뷰 수정
	    @PutMapping("/update/{reviewNo}")
	    public String updateReview(@PathVariable int reviewNo, @RequestBody ReviewVo review, HttpSession session) {
	        Integer userNo = (Integer) session.getAttribute("userNo");
	        if (userNo == null) {
	            return "로그인이 필요합니다.";
	        }
	        boolean success = reviewService.updateReview(reviewNo, userNo, review.getComment(), review.getRating());
	        return success ? "리뷰 수정 성공" : "리뷰 수정 실패";
	    }

	    // 유저가 자신의 리뷰 삭제
	    @DeleteMapping("/delete/{reviewNo}")
	    public String deleteReviewByUser(@PathVariable int reviewNo, HttpSession session) {
	        Integer userNo = (Integer) session.getAttribute("userNo");
	        if (userNo == null) {
	            return "로그인이 필요합니다.";
	        }
	        boolean success = reviewService.deleteReviewByUser(reviewNo, userNo);
	        return success ? "리뷰 삭제 성공" : "리뷰 삭제 실패";
	    }

	    // 어드민이 리뷰 삭제
	    @DeleteMapping("/admin/delete/{reviewNo}")
	    public String deleteReviewByAdmin(@PathVariable int reviewNo) {
	        boolean success = reviewService.deleteReviewByAdmin(reviewNo);
	        return success ? "어드민 리뷰 삭제 성공" : "어드민 리뷰 삭제 실패";
	    }
}
