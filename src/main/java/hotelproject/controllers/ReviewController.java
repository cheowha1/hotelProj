package hotelproject.controllers;

import hotelproject.services.ReviewService;
import hotelproject.repositories.vo.ReviewVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // 리뷰 추가
    @PostMapping
    public ResponseEntity<String> addReview(@RequestBody ReviewVo review) {
        reviewService.insertReview(review);
        return ResponseEntity.ok("리뷰가 등록되었습니다.");
    }

    // 호텔의 리뷰 조회
    @GetMapping("/{hotelName}")
    public ResponseEntity<List<ReviewVo>> getReviews(@PathVariable int hotelName) {
        return ResponseEntity.ok(reviewService.getReivews(hotelName));
    }

    // 호텔의 평균 평점 조회
    @GetMapping("/{hotelName}/rating")
    public ResponseEntity<Double> getAverageRating(@PathVariable int hotelName) {
        return ResponseEntity.ok(reviewService.getAverageRating(hotelName));
    }

    // 호텔의 별점 문자열 반환
    @GetMapping("/{hotelName}/stars")
    public ResponseEntity<String> getStarRating(@PathVariable int hotelName) {
        return ResponseEntity.ok(reviewService.getStarRating(hotelName));
    }

    // 리뷰 수정
    @PutMapping
    public ResponseEntity<String> updateReview(@RequestBody ReviewVo review) {
        reviewService.updateReview(review);
        return ResponseEntity.ok("리뷰가 수정되었습니다.");
    }

    // 리뷰 삭제
    @DeleteMapping
    public ResponseEntity<String> deleteReview(@RequestBody ReviewVo review) {
        reviewService.deleteReview(review);
        return ResponseEntity.ok("리뷰가 삭제되었습니다.");
    }
}
