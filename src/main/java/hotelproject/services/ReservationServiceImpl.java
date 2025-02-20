package hotelproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hotelproject.mappers.ReservationMapper;
import hotelproject.mappers.UserMapper;
import hotelproject.repositories.vo.ReservationVo;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationMapper reservationMapper;
    
    @Autowired
    private UserMapper userMapper;

    // 예약등록
    @Override
    @Transactional
    public int insertReservation(ReservationVo reservation) {
        return reservationMapper.insertReservation(reservation);
    }
    
    //	예약 상세조회
    @Override
    public ReservationVo getReservationById(int reservationNo) {
        return reservationMapper.selectReservationById(reservationNo);
    }
    
    //	예약 목록조회
    @Override
    public List<ReservationVo> getAllReservations() {
        return reservationMapper.selectAllReservations();
    }
    
    //	예약 수정
    @Override
    public int updateReservation(ReservationVo reservation) {
        return reservationMapper.updateReservation(reservation);
    }

    //	예약 취소
    @Override
    public int deleteReservation(int reservationNo) {
        return reservationMapper.deleteReservation(reservationNo);
    }

    @Override
    public List<ReservationVo> getUserReservations(String email) {
        return reservationMapper.selectReservationsByEmail(email);
    }
    
    // **예약 시 포인트 사용**
    @Override
    @Transactional
    public boolean usePointForReservationPoints(int userNo, int amount) {
        int currentPoints = userMapper.getUserTotalPoints(userNo);
        if (currentPoints < amount) {
            return false; // 포인트 부족
        }

        int updatedRows = userMapper.usePoints(userNo, amount);
        if (updatedRows > 0) {
            // 포인트 사용 내역 저장
            userMapper.insertPointLog(userNo, amount, "use", "호텔 예약 결제");
            return true;
        }
        return false;
    }
 
    //	예약 실패 시 포인트 차감없음
    @Override
    @Transactional
    public int insertReservation(ReservationVo reservation, boolean usePoints) {
        if (usePoints) {
            int currentPoints = userMapper.getUserTotalPoints(reservation.getUserNo());

            if (currentPoints < reservation.getTotalPrice()) {
                return -1; // 포인트 부족
            }

            boolean isPointUsed = usePointForReservationPoints(reservation.getUserNo(), reservation.getTotalPrice());
            if (!isPointUsed) {
                return -1; // 포인트 사용 실패
            }
        }
        
        int result = reservationMapper.insertReservation(reservation);
        return result;
    }
    
    // **예약 취소 시 포인트 반환**
    @Override
    @Transactional
    public void refundPointsForCanceledReservation(int userNo, int amount) {
        userMapper.earnPoints(userNo, amount);
        userMapper.insertPointLog(userNo, amount, "refund", "호텔 예약 취소로 인한 포인트 반환");
    }
     
    //	예약 후 포인트 차감
    @Override
    @Transactional
    public int insertReservationWithPoints(ReservationVo reservation, boolean usePoints) {
        if (usePoints) {
            int currentPoints = userMapper.getUserTotalPoints(reservation.getUserNo()); // ✅ 오타 수정

            if (currentPoints < reservation.getTotalPrice()) {
                return -1; // 포인트 부족
            }
            
            // 예약 시 포인트 부족할 시 포인트 차감 실패
            int updatedRows = userMapper.usePoints(reservation.getUserNo(), reservation.getTotalPrice());
            if (updatedRows == 0) {
                return -1; // 포인트 차감 실패 
            }
            
            userMapper.insertPointLog(reservation.getUserNo(), reservation.getTotalPrice(), "use", "호텔 예약 결제");
        }
    	int result = reservationMapper.insertReservation(reservation);
    	return result;
    	
    }
    
    
}
    
