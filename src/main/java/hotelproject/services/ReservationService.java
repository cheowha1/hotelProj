package hotelproject.services;

import java.util.List;
import hotelproject.repositories.vo.ReservationVo;

public interface ReservationService {
    // 유저가 포인트를 사용하여 호텔 예약
    boolean reserveHotelWithPoints(int userNo, int hotelNo, int amount);

    // 유저의 호텔 예약 목록 조회
    List<ReservationVo> getUserReservations(int userNo);

    // 유저의 호텔 예약 내역 조회 (히스토리)
    List<ReservationVo> getReservationHistory(int userNo);

    // 유저가 호텔 예약 취소 (포인트 환불 포함)
    boolean cancelReservation(int reservationNo, int userNo, int amount);
}
