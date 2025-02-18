package hotelproject.repositories;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import hotelproject.repositories.vo.ReservationVo;
import java.util.List;

@Repository
public class ReservationDaoImpl implements ReservationDao {

    @Autowired	//	sql문 설정
    private SqlSession sqlSession;

    @Override	//	예약등록(추가)
    public int insertReservation(ReservationVo reservation) {
        return sqlSession.insert("reservation.insertReservation", reservation);
    }

    @Override	//	예약 상세조회
    public ReservationVo selectReservationById(int reservationNo) {
        return sqlSession.selectOne("reservation.selectReservationById", reservationNo);
    }

    @Override	//	예약 목록조회
    public List<ReservationVo> selectAllReservations() {
        return sqlSession.selectList("reservation.selectAllReservations");
    }

    @Override	//	예약수정
    public int updateReservation(ReservationVo reservation) {
        return sqlSession.update("reservation.updateReservation", reservation);
    }

    @Override	//	예약취소
    public int deleteReservation(int reservationNo) {
        return sqlSession.delete("reservation.deleteReservation", reservationNo);
    }
}
