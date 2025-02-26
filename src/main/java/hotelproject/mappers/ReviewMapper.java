package hotelproject.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import hotelproject.repositories.vo.ReviewVo;

@Mapper
public interface ReviewMapper {
	
	 void insertReview(ReviewVo review);
	 ReviewVo getReviewById(@Param("reviewNo") int reviewNo);
	 void updateReview(@Param("reviewNo") int reviewNo, @Param("comment") String comment, @Param("rating") int rating);
	 void deleteReview(@Param("reviewNo") int reviewNo);
	 Double calculateAverageRating(@Param("hotelNo") int hotelNo);
	 List<ReviewVo> getAllReviews();
	 List<ReviewVo> getUserReviews(@Param("userNo") int userNo);
	 List<ReviewVo> getHotelReviews(@Param("hotelNo") int hotelNo);
}
