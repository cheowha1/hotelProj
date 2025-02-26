package hotelproject.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotelproject.controllers.AddReviewRequest;
import hotelproject.controllers.UpdateReviewRequest;
import hotelproject.mappers.HotelMapper;
import hotelproject.mappers.ReviewMapper;
import hotelproject.repositories.vo.ReviewVo;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private HotelMapper hotelMapper;

    @Override
    public boolean addReview(int hotelNo, AddReviewRequest request) {
        reviewMapper.insertReview(new ReviewVo(hotelNo, request.userId(), request.comment(), request.rating()));
        updateHotelRating(hotelNo);
        return true;
    }

    @Override
    public boolean updateReview(int reviewNo,UpdateReviewRequest request) {
        ReviewVo existingReview = reviewMapper.getReviewById(reviewNo);
        if (existingReview == null || !existingReview.getUserId().equals(request.userId())) {
            throw new IllegalArgumentException("리뷰를 찾을 수 없거나 수정할 권한이 없습니다.");
        }
        reviewMapper.updateReview(reviewNo, request.comment(), request.rating());
        updateHotelRating(existingReview.getHotelNo());
        return true;
    }

    @Override
    public boolean deleteReview(int reviewNo, String userId) {
        ReviewVo existingReview = reviewMapper.getReviewById(reviewNo);
        if (existingReview == null || !existingReview.getUserId().equals(userId)) {
            throw new IllegalArgumentException("리뷰를 찾을 수 없거나 삭제할 권한이 없습니다.");
        }
        reviewMapper.deleteReview(reviewNo);
        updateHotelRating(existingReview.getHotelNo());
        return true;
    }
    
    
    @Override
    public boolean deleteReviewByAdmin(int reviewNo) {
        ReviewVo existingReview = reviewMapper.getReviewById(reviewNo);
        if (existingReview == null) {
            throw new IllegalArgumentException("삭제할 리뷰를 찾을 수 없습니다.");
        }
        reviewMapper.deleteReview(reviewNo);
        updateHotelRating(existingReview.getHotelNo());
        return true;
    }
    
    @Override
    public List<ReviewVo> getAllReviews() {
        return reviewMapper.getAllReviews();
    }

    @Override
    public List<ReviewVo> getUserReviews(int userNo) {
        return reviewMapper.getUserReviews(userNo);
    }

    @Override
    public List<ReviewVo> getHotelReviews(int hotelNo) {
        return reviewMapper.getHotelReviews(hotelNo);
    }
    
    private void updateHotelRating(int hotelNo) {
        Double newAverageRating = reviewMapper.calculateAverageRating(hotelNo);
        hotelMapper.updateHotelRating(hotelNo, newAverageRating);
    }
}