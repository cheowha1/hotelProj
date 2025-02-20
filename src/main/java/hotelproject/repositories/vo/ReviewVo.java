package hotelproject.repositories.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReviewVo {
    private int userid; // 회원 ID
    private int hotelName;	// 호텔 이름
    private String reviewText; // 리뷰 내용
    private int rating; // 별점 (1~5)
    private Date createdAt; // 생성 날짜
    private int AverageRating; // 평균별점
}