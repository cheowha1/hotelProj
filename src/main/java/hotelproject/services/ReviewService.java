package hotelproject.services;

import java.util.List;

import hotelproject.repositories.vo.ReviewVo;

public interface ReviewService {
    // 리뷰 추가
    boolean addReview(ReviewVo review);

    // 특정 호텔의 리뷰 목록 조회
    List<ReviewVo> getReviewsByHotel(int hotelNo);

    // 특정 유저의 리뷰 목록 조회
    List<ReviewVo> getReviewsByUser(int userNo);

    // 리뷰 수정
    boolean updateReview(int reviewNo, int userNo, String comment, int rating);

    // 유저가 자신의 리뷰 삭제
    boolean deleteReviewByUser(int reviewNo, int userNo);

    // 어드민이 리뷰 삭제
    boolean deleteReviewByAdmin(int reviewNo);
}
