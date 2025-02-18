package hotelproject.repositories;

import java.util.List;
import hotelproject.repositories.vo.ReservationVo;

public interface ReservationDao {
	int insertReservation(ReservationVo reservation);	//	예약 등록(추가)
	ReservationVo selectReservationById(int reservationNo);	//	예약 상세조회
	List<ReservationVo> selectAllReservations();	//	예약 목록 조회
	int updateReservation(ReservationVo reservation);	//	예약 수정
	int deleteReservation(int reservationNo);	//	예약 취소
}
