package hotelproject.services;

import java.util.List;

import hotelproject.repositories.vo.PointHistoryVo;

public interface ReservationService {
   
	 boolean bookHotel(String userId, int hotelId, int cost);
	 boolean cancelReservation(String userId, int reservationId);
	 List<PointHistoryVo> getPointHistory(String userId);
}
