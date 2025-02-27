package hotelproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hotelproject.controllers.ReservationRequest;
import hotelproject.mappers.ReservationMapper;
import hotelproject.mappers.RoomMapper;
import hotelproject.repositories.vo.ReservationVo;

@Service
public class ReservationServiceImpl implements ReservationService {

	 private final ReservationMapper reservationMapper;
	    private final PointService pointService; // ✅ 포인트 서비스 추가
	    private final RoomMapper roomMapper;

	    @Autowired
	    public ReservationServiceImpl(ReservationMapper reservationMapper, 
	                                  PointService pointService, // ✅ 추가
	                                  RoomMapper roomMapper) {   // ✅ 추가
	        this.reservationMapper = reservationMapper;
	        this.pointService = pointService;  // ✅ 추가
	        this.roomMapper = roomMapper;      // ✅ 추가
	    }

	    @Override
	    @Transactional
	    public boolean bookReservation(ReservationRequest request, String userId) {
	    	 if (reservationMapper.checkExistingReservation(userId, request.roomId(), request.checkInDate(), request.checkOutDate()) > 0) {
	    	        return false; // 🚫 중복 예약이면 예약 불가능
	    	    }
	    	
	        // ✅ 1. 현재 유저의 포인트 조회
	        int userPoints = pointService.getUserPoints(userId);
	        if (userPoints < request.totalPrice()) {
	            return false; // 포인트 부족으로 예약 실패
	        }

	        // ✅ 2. 예약 정보 저장
	        ReservationVo reservation = new ReservationVo(
	            userId, request.hotelId(), request.roomId(), request.roomName(),
	            request.checkInDate(), request.checkOutDate(),
	            request.adultCount(), request.childCount(), request.totalPrice(), "예약 중"
	        );
	        reservationMapper.insertReservation(reservation);

	        // ✅ 3. 포인트 차감
	        pointService.deductUserPoints(userId, request.totalPrice());

	        // ✅ 4. 포인트 사용 내역 기록
	        pointService.insertPointHistory(userId, -request.totalPrice(), "사용");

	        // ✅ 5. 방 개수 감소
	        roomMapper.updateRoomAvailability(request.roomId());

	        return true;
	    }

	    // ✅ 인터페이스의 getUserReservations 메서드 구현 (오류 해결)
	    @Override
	    public List<ReservationVo> getUserReservations(String userId) {
	        return reservationMapper.getReservationsByUserId(userId);
	    }

	    @Transactional
	    @Override
	    public void cancelReservation(int reservationId) {
	        System.out.println("예약 취소 요청: " + reservationId); // 디버깅 로그
	        reservationMapper.cancelReservation(reservationId);
	        System.out.println("예약 취소 완료"); // 디버깅 로그
	    }

}
    
