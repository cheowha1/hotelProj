package hotelproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotelproject.mappers.PointMapper;
import hotelproject.mappers.UserMapper;
import hotelproject.repositories.vo.PointHistoryVo;
import hotelproject.repositories.vo.UserVo;
import jakarta.servlet.http.HttpSession;

@Service
public class PointServiceImpl implements PointService {
	
	 @Autowired
	    private PointMapper pointMapper; // 포인트 관련 DB 작업을 담당
	    @Autowired
	    private UserMapper userMapper;   // 유저 관련 DB 작업을 담당

	    @Override
	    public void chargePoints(int amount, HttpSession session) {
	        String userId = (String) session.getAttribute("userId");
	        if (userId == null) {
	            throw new IllegalArgumentException("로그인 필요");
	        }

	        // 10% 추가된 포인트 계산
	        int totalAmount = amount + (int)(amount * 0.1);

	        // 포인트 충전 (기본 포인트 + 10% 추가 포인트)
	        pointMapper.updateUserPoints(userId, totalAmount);

	        // 포인트 충전 내역 기록
	        pointMapper.insertPointHistory(userId, amount, "충전");
	        pointMapper.insertPointHistory(userId, (int)(amount * 0.1), "충전 보너스");
	    }

	    @Override
	    public boolean usePoints(String userId, int amount) {
	        // 포인트 사용 로직
	        int currentPoints = userMapper.getUserById(userId).getPoint();
	        if (currentPoints < amount) {
	            throw new IllegalArgumentException("포인트가 부족합니다.");
	        }

	        // 포인트 차감
	        userMapper.updateUserPoints(userId, currentPoints - amount);
	        pointMapper.insertPointUsageHistory(userId, amount, "사용");

	        // 포인트 사용 후 등급 변경
	        updateUserGrade(userId);
	        return true; // 성공적으로 포인트가 사용된 경우 true 반환
	    }


	    @Override
	    public void updateUserGrade(String userId) {
	        UserVo user = userMapper.getUserById(userId);
	        int totalUsedPoints = user.getTotalUsedPoint();
	        String grade = "일반"; // 기본 등급

	        // 포인트 사용에 따른 등급 변경 로직
	        if (totalUsedPoints >= 10000) {
	            grade = "Silver";
	        } else if (totalUsedPoints >= 50000) {
	            grade = "VIP";
	        } else if (totalUsedPoints >= 100000) {
	            grade = "VVIP";
	        }

	        // 등급 업데이트
	        userMapper.updateUserGrade(userId, grade);
	    }

	    @Override
	    public List<PointHistoryVo> getPointHistory(String userId) {
	        // 포인트 내역 조회
	        return pointMapper.getPointHistory(userId);
	    }
}