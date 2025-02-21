package hotelproject.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hotelproject.mappers.ReviewMapper;
import hotelproject.repositories.vo.ReviewVo;

@Service
public class ReviewServiceImpl implements ReviewService {

	  @Autowired
	    private ReviewMapper reviewMapper;

	    // 리뷰 추가
	    @Override
	    @Transactional
	    public boolean addReview(ReviewVo review) {
	        boolean success = reviewMapper.addReview(review) > 0;
	        if (success) {
	            reviewMapper.updateHotelRating(review.getHotelNo()); // 별점 평균 업데이트
	        }
	        return success;
	    }

	    // 특정 호텔의 리뷰 목록 조회
	    @Override
	    public List<ReviewVo> getReviewsByHotel(int hotelNo) {
	        return reviewMapper.getReviewsByHotel(hotelNo);
	    }

	    // 특정 유저의 리뷰 목록 조회
	    @Override
	    public List<ReviewVo> getReviewsByUser(int userNo) {
	        return reviewMapper.getReviewsByUser(userNo);
	    }

	    // 리뷰 수정
	    @Override
	    @Transactional
	    public boolean updateReview(int reviewNo, int userNo, String comment, int rating) {
	        boolean success = reviewMapper.updateReview(new ReviewVo(reviewNo, userNo, comment, rating)) > 0;
	        if (success) {
	            ReviewVo review = reviewMapper.getReviewById(reviewNo);
	            reviewMapper.updateHotelRating(review.getHotelNo()); // 별점 평균 업데이트
	        }
	        return success;
	    }

	    // 유저가 자신의 리뷰 삭제
	    @Override
	    @Transactional
	    public boolean deleteReviewByUser(int reviewNo, int userNo) {
	        ReviewVo review = reviewMapper.getReviewById(reviewNo);
	        boolean success = reviewMapper.deleteReviewByUser(reviewNo, userNo) > 0;
	        if (success) {
	            reviewMapper.updateHotelRating(review.getHotelNo()); // 별점 평균 업데이트
	        }
	        return success;
	    }

	    // 어드민이 리뷰 삭제
	    @Override
	    @Transactional
	    public boolean deleteReviewByAdmin(int reviewNo) {
	        ReviewVo review = reviewMapper.getReviewById(reviewNo);
	        boolean success = reviewMapper.deleteReviewByAdmin(reviewNo) > 0;
	        if (success) {
	            reviewMapper.updateHotelRating(review.getHotelNo()); // 별점 평균 업데이트
	        }
	        return success;
	    }
}