package hotelproject.services;

import java.util.List;
import hotelproject.repositories.vo.ReservationVo;

public interface ReservationService {
    int insertReservation(ReservationVo reservation);	//	예약등록(추가)
    ReservationVo getReservationById(int reservationNo);	//	예약 상세조회
    List<ReservationVo> getAllReservations();	//	예약 목록조회
    int updateReservation(ReservationVo reservation);	//	예약수정
    int deleteReservation(int reservationNo);	//	예약취소
    List<ReservationVo> getUserReservations(String email); // 예약 내역 조회
}
