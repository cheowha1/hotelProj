package hotelproject.repositories.vo;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewVo {
	  private int reviewNo;
	    private int userNo;
	    private int hotelNo;
	    private String comment;
	    private int rating;
	    private LocalDateTime createdAt;
	    private LocalDateTime updatedAt;

	    // 생성자
	    public ReviewVo() {}

	    public ReviewVo(int reviewNo, int userNo, String comment, int rating) {
	        this.reviewNo = reviewNo;
	        this.userNo = userNo;
	        this.comment = comment;
	        this.rating = rating;
	    }

	    // Getter & Setter
	    public int getReviewNo() { return reviewNo; }
	    public void setReviewNo(int reviewNo) { this.reviewNo = reviewNo; }

	    public int getUserNo() { return userNo; }
	    public void setUserNo(int userNo) { this.userNo = userNo; }

	    public int getHotelNo() { return hotelNo; }
	    public void setHotelNo(int hotelNo) { this.hotelNo = hotelNo; }

	    public String getComment() { return comment; }
	    public void setComment(String comment) { this.comment = comment; }

	    public int getRating() { return rating; }
	    public void setRating(int rating) { this.rating = rating; }

	    public LocalDateTime getCreatedAt() { return createdAt; }
	    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

	    public LocalDateTime getUpdatedAt() { return updatedAt; }
	    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
	}