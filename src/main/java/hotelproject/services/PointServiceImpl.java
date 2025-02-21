package hotelproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hotelproject.mappers.PointMapper;
import hotelproject.repositories.vo.PointVo;

@Service
public class PointServiceImpl implements PointService {

	   @Autowired
	    private PointMapper pointmapper;

	    // 포인트 사용하여 호텔 예약
	    @Override
	    @Transactional
	    public boolean usePointsForReservation(int userNo, int amount) {
	        int currentPoints = pointmapper.getUserTotalPoints(userNo);
	        if (currentPoints >= amount) {
	            PointVo point = new PointVo();
	            point.setUserNo(userNo);
	            point.setPointAmount(-amount);
	            point.setPointType("RESERVATION");
	            pointmapper.insertReservationPoint(point);
	            return true;
	        }
	        return false;
	    }

	    // 포인트 사용 내역 조회 (히스토리)
	    @Override
	    public List<PointVo> getPointHistory(int userNo) {
	        return pointmapper.getPointHistory(userNo);
	    }

	    // 현재 유저의 포인트 조회
	    @Override
	    public int getUserTotalPoints(int userNo) {
	        return pointmapper.getUserTotalPoints(userNo);
	    }
}