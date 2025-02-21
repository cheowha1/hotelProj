package hotelproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hotelproject.mappers.PointMapper;
import hotelproject.mappers.ReservationMapper;
import hotelproject.repositories.vo.PointVo;
import hotelproject.repositories.vo.ReservationVo;

@Service
public class ReservationServiceImpl implements ReservationService {

	 @Autowired
	    private ReservationMapper reservationMapper;

	    @Autowired
	    private PointMapper pointMapper;

	    // 유저가 포인트를 사용하여 호텔 예약
	    @Override
	    @Transactional
	    public boolean reserveHotelWithPoints(int userNo, int hotelNo, int amount) {
	        int currentPoints = pointMapper.getUserTotalPoints(userNo);
	        if (currentPoints >= amount) {
	            // 예약 추가
	            ReservationVo reservation = new ReservationVo(userNo, hotelNo, amount);
	            reservationMapper.insertReservation(reservation);
	            
	            // 포인트 차감
	            PointVo point = new PointVo(userNo, -amount, "RESERVATION");
	            pointMapper.insertReservationPoint(point);
	            
	            return true;
	        }
	        return false;
	    }

	    // 유저의 호텔 예약 목록 조회
	    @Override
	    public List<ReservationVo> getUserReservations(int userNo) {
	        return reservationMapper.getUserReservations(userNo);
	    }

	    // 유저의 호텔 예약 내역 조회 (히스토리)
	    @Override
	    public List<ReservationVo> getReservationHistory(int userNo) {
	        return reservationMapper.getReservationHistory(userNo);
	    }

	    // 유저가 호텔 예약 취소 (포인트 환불 포함)
	    @Override
	    @Transactional
	    public boolean cancelReservation(int reservationNo, int userNo, int amount) {
	        ReservationVo reservation = reservationMapper.getReservationById(reservationNo);
	        if (reservation != null && reservation.getUserNo() == userNo) {
	            // 예약 삭제
	            reservationMapper.deleteReservation(reservationNo);
	            
	            // 포인트 환불
	            PointVo point = new PointVo(userNo, amount, "CANCEL_RESERVATION");
	            pointMapper.insertReservationPoint(point);
	            
	            return true;
	        }
	        return false;
	    }
    
}
    
