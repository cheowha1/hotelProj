package hotelproject.services;

import java.util.List;

import hotelproject.repositories.vo.ReservationVo;
import jakarta.servlet.http.HttpSession;

public interface ReservationService {
   
	 boolean bookHotel(HttpSession session, int hotelId, int cost); 
	 boolean cancelReservation(HttpSession session, int reservationId); 
	 List<ReservationVo> getUserReservations(HttpSession session);
}
