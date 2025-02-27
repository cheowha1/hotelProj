package hotelproject.controllers;

public record ReservationRequest(
		 int hotelId,  
		    int roomId, 
		    String roomName,
		    String checkInDate, 
		    String checkOutDate,  
		    int adultCount,
		    int childCount,
		    int totalPrice
	) {}