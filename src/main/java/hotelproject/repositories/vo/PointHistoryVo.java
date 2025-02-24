package hotelproject.repositories.vo;

import java.util.Date;

public class PointHistoryVo {

	private String userId;
	    private int amount;
	    private String type; // 충전, 사용
	    private Date createdAt;

	    public PointHistoryVo(String userId, int amount, String type, Date createdAt) {
	        this.userId = userId;
	        this.amount = amount;
	        this.type = type;
	        this.createdAt = createdAt;
	    }

	
		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public int getAmount() {
			return amount;
		}

		public void setAmount(int amount) {
			this.amount = amount;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public Date getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}
	    
}
