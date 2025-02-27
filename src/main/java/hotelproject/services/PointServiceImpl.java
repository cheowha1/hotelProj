package hotelproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotelproject.mappers.PointMapper;
import hotelproject.mappers.UserMapper;
import hotelproject.repositories.vo.PointHistoryVo;
import hotelproject.repositories.vo.UserVo;

@Service
public class PointServiceImpl implements PointService {
	
	 @Autowired
	    private PointMapper pointMapper;

	    @Autowired
	    private UserMapper userMapper;

	    // 포인트 충전
	    @Override
	    public void chargePoints(int amount, String userId) {
	        // 10% 추가 포인트 지급
	        int totalAmount = amount + (int)(amount * 0.1);
	        
	        // 포인트 충전
	        pointMapper.updateUserPoints(userId, totalAmount);
	        
	        // 충전 내역 기록
	        pointMapper.insertPointHistory(userId, totalAmount, "충전");

	        // 포인트 사용 후 등급 업데이트
	        updateUserGrade(userId);
	    }

	    // 포인트 사용
	    @Override
	    public void usePoints(String userId, int amount) {
	        // 현재 포인트 조회
	        int currentPoints = userMapper.getUserById(userId).getPoint();
	        if (currentPoints < amount) {
	            throw new IllegalArgumentException("포인트가 부족합니다.");
	        }

	        // 포인트 차감
	        userMapper.updateUserPoints(userId, currentPoints - amount);
	        pointMapper.insertPointHistory(userId, amount, "사용");

	        // 포인트 사용 후 등급 변경
	        updateUserGrade(userId);
	    }

	    // 포인트 내역 조회
	    @Override
	    public List<PointHistoryVo> getPointHistory(String userId) {
	        return pointMapper.getPointHistory(userId);
	    }

	    // 포인트 사용에 따른 등급 변경
	    @Override
	    public void updateUserGrade(String userId) {
	        // 유저 정보 조회
	        UserVo user = userMapper.getUserById(userId);
	        int totalUsedPoints = user.getTotalUsedPoint();
	        String grade = "일반";

	        if (totalUsedPoints >= 300000) {
	            grade = "VVIP";
	        } else if (totalUsedPoints >= 100000) {
	            grade = "VIP";
	        } else if (totalUsedPoints >= 30000) {
	            grade = "Silver";
	        }

	        // 등급 업데이트
	        userMapper.updateUserGrade(userId, grade);
	    }
}