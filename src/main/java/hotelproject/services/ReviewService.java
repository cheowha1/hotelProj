package hotelproject.services;

import java.util.List;

import hotelproject.repositories.vo.ReviewVo;

public interface ReviewService {
   
	  boolean addReview(int hotelNo, String userId, String comment, int rating);
	  boolean updateReview(int reviewNo, String userId, String comment, int rating);
	  boolean deleteReview(int reviewNo, String userId);
	  boolean deleteReviewByAdmin(int reviewNo);
	  List<ReviewVo> getAllReviews();
	  List<ReviewVo> getUserReviews(int userNo);
	  List<ReviewVo> getHotelReviews(int hotelNo);
}
