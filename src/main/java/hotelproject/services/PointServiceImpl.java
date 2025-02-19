package hotelproject.services;

import hotelproject.mappers.PointMapper;
import hotelproject.repositories.vo.PointVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PointServiceImpl implements PointService {

    @Autowired
    private PointMapper pointmapper;

    // 포인트 적립
    @Override
    @Transactional
    public void earnPoints(int userNo, int amount) {
        PointVo point = new PointVo();
        point.setUserNo(userNo);
        point.setPointAmount(amount);
        point.setPointType("EARN");
        pointmapper.insertPoint(point);
    }

    //	포인트 사용기능
    @Override
    @Transactional
    public boolean usePoints(int userNo, int amount) {
        int currentPoints = pointmapper.getUserTotalPoints(userNo);
        if (currentPoints < amount) {
            return false; // 포인트 부족
        }

        PointVo point = new PointVo();
        point.setUserNo(userNo);
        point.setPointAmount(-amount);
        point.setPointType("USE");
        pointmapper.insertPoint(point);
        return true;
    }

    //	회원의 총 포인트 조회
    @Override
    public int getUserTotalPoints(int userNo) {
        Integer totalPoints = pointmapper.getUserTotalPoints(userNo);
        return totalPoints != null ? totalPoints : 0;
    }
    
    //	포인트 내역 조회
    @Override
    public List<PointVo> getUserPointHistory(int userNo) {
        return pointmapper.getUserPointHistory(userNo);
    }
}