package hotelproject.services;

import java.util.List;

import hotelproject.controllers.AddReviewRequest;
import hotelproject.controllers.UpdateReviewRequest;
import hotelproject.repositories.vo.ReviewVo;

public interface ReviewService {
   
	  boolean addReview(int hotelNo, AddReviewRequest request);
	  boolean updateReview(int reviewNo, UpdateReviewRequest request);
	  boolean deleteReview(int reviewNo, String userId);
	  boolean deleteReviewByAdmin(int reviewNo);
	  List<ReviewVo> getAllReviews();
	  List<ReviewVo> getUserReviews(int userNo);
	  List<ReviewVo> getHotelReviews(int hotelNo);
}
