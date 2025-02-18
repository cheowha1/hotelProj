package hotelproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import hotelproject.repositories.ReservationDao;
import hotelproject.repositories.vo.ReservationVo;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationDao reservationDao;

    // 예약등록
    @Override
    @Transactional
    public int insertReservation(ReservationVo reservation) {
        return reservationDao.insertReservation(reservation);
    }
    
    //	예약 상세조회
    @Override
    public ReservationVo getReservationById(int reservationNo) {
        return reservationDao.selectReservationById(reservationNo);
    }
    
    //	예약 목록조회
    @Override
    public List<ReservationVo> getAllReservations() {
        return reservationDao.selectAllReservations();
    }
    
    //	예약 수정
    @Override
    public int updateReservation(ReservationVo reservation) {
        return reservationDao.updateReservation(reservation);
    }

    //	예약 취소
    @Override
    public int deleteReservation(int reservationNo) {
        return reservationDao.deleteReservation(reservationNo);
    }
}