package hotelproject.repositories.vo;

import java.util.Date;

public class ReviewVo {
	 
	 private int reviewNo;
	 private int hotelNo;
	 private String userId;
	 private String comment;
	 private int rating;
	 private Date createdAt;

	 public ReviewVo(int hotelNo, String userId, String comment, int rating) {
		 this.hotelNo = hotelNo;
	     this.userId = userId;
	     this.comment = comment;
	     this.rating = rating;
	     this.createdAt = new Date();
	  }

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public int getHotelNo() {
		return hotelNo;
	}

	public void setHotelNo(int hotelNo) {
		this.hotelNo = hotelNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	 
	 
}