package hotelproject.repositories.vo;

public class HotelVo {

	 private int hotelNo;
	    private String name;
	    private String location;
	    private String phone;
	    private int availableRooms;
	    private double rating;

	    public HotelVo(int hotelNo, String name, String location, String phone, int availableRooms, double rating) {
	        this.hotelNo = hotelNo;
	        this.name = name;
	        this.location = location;
	        this.phone = phone; 
	        this.availableRooms = availableRooms;
	        this.rating = rating;
	    }

		public int getHotelNo() {
			return hotelNo;
		}

		public void setHotelNo(int hotelNo) {
			this.hotelNo = hotelNo;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public int getAvailableRooms() {
			return availableRooms;
		}

		public void setAvailableRooms(int availableRooms) {
			this.availableRooms = availableRooms;
		}

		public double getRating() {
			return rating;
		}

		public void setRating(double rating) {
			this.rating = rating;
		}
	    
	    
}