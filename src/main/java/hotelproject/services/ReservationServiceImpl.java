package hotelproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import hotelproject.mappers.ReservationMapper;
import hotelproject.repositories.vo.ReservationVo;
import hotelproject.mappers.ReservationMapper;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationMapper reservationMapper;

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
    
    @Autowired
    private ReservationMapper reservationMapper;

    @Override
    public List<ReservationVo> getUserReservations(String email) {
        return reservationMapper.selectReservationsByEmail(email);
    }
}