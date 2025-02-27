package hotelproject.services;

import java.util.List;

import hotelproject.controllers.ReservationRequest;
import hotelproject.repositories.vo.ReservationVo;

public interface ReservationService {
   
//	 boolean bookHotel(HttpSession session, int hotelId, int cost); 
//	 boolean cancelReservation(HttpSession session, int reservationId); 
//	 List<ReservationVo> getUserReservations(HttpSession session);
	  boolean bookReservation(ReservationRequest request, String userId);
	    List<ReservationVo> getUserReservations(String userId);
	    void cancelReservation(int reservationId);
}
