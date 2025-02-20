package hotelproject.services;

import java.util.List;

import hotelproject.repositories.vo.ReviewVo;

public interface ReviewService {
	void insertReview(ReviewVo review);	//	리뷰 등록
	List<ReviewVo> getReviews(int hotelName); // 호텔이름으로 리뷰목록 조회
	double getAverageRating(int hotelName);	//	호텔의 평균 별점 조회
	void updateReview(ReviewVo review);	//	리뷰 수정
	void deleteReview(ReviewVo review);	//	리뷰 삭제
	String getStarRating(int hotelName);	//	별점 등급을 계산하여 반환하는 메서드\
	void insertRating(ReviewVo review);	//	별점 등록
}
