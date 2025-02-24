package hotelproject.repositories.vo;

import java.util.Date;

public class ReservationVo {
	
	 private int reservationId;
	    private String userId;
	    private int hotelId;
	    private int cost;
	    private Date createdAt;

	    public ReservationVo(String userId, int hotelId, int cost, Date createdAt) {
	        this.userId = userId;
	        this.hotelId = hotelId;
	        this.cost = cost;
	        this.createdAt = createdAt;
	    }

		public int getReservationId() {
			return reservationId;
		}

		public void setReservationId(int reservationId) {
			this.reservationId = reservationId;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public int getHotelId() {
			return hotelId;
		}

		public void setHotelId(int hotelId) {
			this.hotelId = hotelId;
		}

		public int getCost() {
			return cost;
		}

		public void setCost(int cost) {
			this.cost = cost;
		}

		public Date getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}
	    
	    
}