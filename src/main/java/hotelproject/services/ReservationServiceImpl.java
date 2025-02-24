package hotelproject.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotelproject.mappers.PointMapper;
import hotelproject.mappers.ReservationMapper;
import hotelproject.mappers.UserMapper;
import hotelproject.repositories.vo.PointHistoryVo;
import hotelproject.repositories.vo.ReservationVo;

@Service
public class ReservationServiceImpl implements ReservationService {

	 @Autowired
	    private UserMapper userMapper;
	    @Autowired
	    private ReservationMapper reservationMapper;
	    @Autowired
	    private PointMapper pointMapper;

	    @Override
	    public boolean bookHotel(String userId, int hotelId, int cost) {
	        int currentPoints = userMapper.getUserPoints(userId);
	        if (currentPoints < cost) {
	            throw new IllegalArgumentException("포인트가 부족합니다.");
	        }
	        
	        // 포인트 차감
	        userMapper.updateUserPoints(userId, -cost);
	        
	        // 예약 내역 저장
	        ReservationVo reservation = new ReservationVo(userId, hotelId, cost, new Date());
	        reservationMapper.insertReservation(reservation);
	        
	        // 포인트 사용 내역 저장
	        PointHistoryVo history = new PointHistoryVo(userId, -cost, "예약", new Date());
	        pointMapper.insertPointHistory(history);
	        
	        return true;
	    }

	    @Override
	    public boolean cancelReservation(String userId, int reservationId) {
	        ReservationVo reservation = reservationMapper.getReservationById(reservationId);
	        if (reservation == null || !reservation.getUserId().equals(userId)) {
	            throw new IllegalArgumentException("예약을 찾을 수 없거나 권한이 없습니다.");
	        }
	        
	        // 포인트 환불
	        userMapper.updateUserPoints(userId, reservation.getCost());
	        
	        // 예약 취소
	        reservationMapper.deleteReservation(reservationId);
	        
	        // 포인트 환불 내역 저장
	        PointHistoryVo history = new PointHistoryVo(userId, reservation.getCost(), "예약 취소", new Date());
	        pointMapper.insertPointHistory(history);
	        
	        return true;
	    }
	    
	    @Override
	    public List<PointHistoryVo> getPointHistory(String userId) {
	        return pointMapper.getPointHistory(userId);
	    }
    
}
    
