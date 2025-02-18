package hotelproject.repositories;

import hotelproject.mappers.PointMapper;
import hotelproject.repositories.vo.PointVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PointDaoImpl implements PointDao {

    @Autowired
    private PointMapper pointMapper;

    //	포인트 적립 및 사용 기록추가
    @Override
    public void insertPoint(PointVo point) {
        pointMapper.insertPoint(point);
    }
    
    // 회원의 총 포인트 조회
    @Override
    public int getUserTotalPoints(int userNo) {
        return pointMapper.getUserTotalPoints(userNo);
    }

    //	회원의 포인트 내역조회
    @Override
    public List<PointVo> getUserPointHistory(int userNo) {
        return pointMapper.getUserPointHistory(userNo);
    }
}
