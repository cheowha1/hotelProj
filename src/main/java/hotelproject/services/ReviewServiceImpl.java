package hotelproject.services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import hotelproject.mappers.ReviewMapper;
import hotelproject.repositories.vo.ReviewVo;
import hotelproject.services.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;
    
    // 	ReviewMapper 주입
    public ReviewServiceImpl(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    //	리뷰등록 
    @Override
    @Transactional
    public void insertReview(ReviewVo review) {
        reviewMapper.insertReview(review);
    }
    
    //	별점등록
 // 별점 등록 기능 추가
    @Override
    @Transactional
    public void insertRating(int hotelName, int userid, int rating) {
        reviewMapper.insertRating(hotelName, userid, rating);
    }

    //	리뷰목록 조회
    @Override
    public List<ReviewVo> getReviews(int hotelName) {
        return reviewMapper.getReviewsByHotel(hotelName);
    }

    //	리뷰 평균별점 조회
    @Override
    public double getAverageRating(int hotelName) {
        Double average = reviewMapper.getAverageRating(hotelName);
        return average != null ? average : 0.0;
    }

    //	리뷰 수정
    @Override
    public void updateReview(ReviewVo review) {
        reviewMapper.updateReview(review);
    }

    //	리뷰 삭제
    @Override
    public void deleteReview(ReviewVo review) {
        reviewMapper.deleteReview(review);
    }

    /**
     * 별점 등급을 계산하여 반환하는 메서드
     */
    @Override
    public String getStarRating(int hotelName) {
        double avgRating = getAverageRating(hotelName);
        return generateStarString(avgRating);
    }

    /**
     * 평균 별점을 "⭐⭐⭐⭐" 형태의 문자열로 변환
     */
    private String generateStarString(double rating) {
        int fullStars = (int) Math.round(rating);
        return "⭐".repeat(fullStars);
    }
}