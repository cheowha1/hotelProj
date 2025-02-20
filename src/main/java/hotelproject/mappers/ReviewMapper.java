package hotelproject.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import hotelproject.repositories.vo.ReviewVo;

@Mapper
public interface ReviewMapper {
	void insertReview(ReviewVo review);	//	리뷰 등록
	List<ReviewVo> getReviewsByHotel(@Param("hotelName") int hotelName); // 호텔이름 리뷰목록 조회
	Double getAverageRating(@Param("hotelName") int hotelName);	// 평균별점 조회
	void updateReview(ReviewVo review);	//	리뷰 수정
	void deleteReview(ReviewVo review);	//	리뷰 삭제
	void insertRating(@Param("hotelName") int hotelName, 
		              @Param("userid") Long userid, @Param("rating") int rating ); // 별점 등록

}
