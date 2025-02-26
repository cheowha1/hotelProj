package hotelproject.repositories.vo;

import java.time.LocalDateTime;

public class CustomerSupportBoardVo {

	  private int id;
	  private Integer boardId;  // 댓글이 속한 게시글 ID (게시글이면 NULL)
	  private String userId;  // 작성자 ID (users.id 참조)
	  private String title;  // 게시글 제목 (댓글은 NULL)
	  private String content;  // 내용 (게시글 또는 댓글)
	  private LocalDateTime createdAt;
	  private LocalDateTime updatedAt;
	  
	  public int getId() {
		  return id;
	  }
	  public void setId(int id) {
		  this.id = id;
	  }
	  public Integer getBoardId() {
		  return boardId;
	  }
	  public void setBoardId(Integer boardId) {
		  this.boardId = boardId;
	  }
	  public String getUserId() {
		  return userId;
	  }
	  public void setUserId(String userId) {
		  this.userId = userId;
	  }
	  public String getTitle() {
		  return title;
	  }
	  public void setTitle(String title) {
		  this.title = title;
	  }
	  public String getContent() {
		  return content;
	  }
	  public void setContent(String content) {
		  this.content = content;
	  }
	  public LocalDateTime getCreatedAt() {
		  return createdAt;
	  }
	  public void setCreatedAt(LocalDateTime createdAt) {
		  this.createdAt = createdAt;
	  }
	  public LocalDateTime getUpdatedAt() {
		  return updatedAt;
	  }
	  public void setUpdatedAt(LocalDateTime updatedAt) {
		  this.updatedAt = updatedAt;
	  }
		  
	  
}
