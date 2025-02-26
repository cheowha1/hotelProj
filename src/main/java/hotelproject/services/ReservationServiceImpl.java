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
import jakarta.servlet.http.HttpSession;

@Service
public class ReservationServiceImpl implements ReservationService {

		@Autowired
	    private UserMapper userMapper;
	    @Autowired
	    private ReservationMapper reservationMapper;
	    @Autowired
	    private PointMapper pointMapper;

	    @Override
	    public boolean bookHotel(HttpSession session, int hotelId, int cost) {
	        String userId = (String) session.getAttribute("userId"); // ✅ 세션에서 userId 가져오기
	        if (userId == null) {
	            throw new IllegalArgumentException("로그인이 필요합니다.");
	        }

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
	    public boolean cancelReservation(HttpSession session, int reservationId) {
	        String userId = (String) session.getAttribute("userId"); // ✅ 세션에서 userId 가져오기
	        if (userId == null) {
	            throw new IllegalArgumentException("로그인이 필요합니다.");
	        }

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
	    public List<ReservationVo> getUserReservations(HttpSession session) {
	        String userId = (String) session.getAttribute("userId"); // ✅ 세션에서 userId 가져오기
	        if (userId == null) {
	            throw new IllegalArgumentException("로그인이 필요합니다.");
	        }

	        return reservationMapper.getUserReservations(userId);
	    }
    
}
    
