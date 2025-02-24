package hotelproject.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotelproject.mappers.PointMapper;
import hotelproject.mappers.UserMapper;
import hotelproject.repositories.vo.PointHistoryVo;

@Service
public class PointServiceImpl implements PointService {
	
	@Autowired
    private UserMapper userMapper;
    @Autowired
    private PointMapper pointMapper;

    @Override
    public boolean chargePoints(String userId, int amount) { 
        // 유저 포인트 충전
        userMapper.updateUserPoints(userId, amount);
        
        // 포인트 내역 저장
        PointHistoryVo history = new PointHistoryVo(userId, amount, "충전", new Date());
        pointMapper.insertPointHistory(history);
        
        return true;
    }
    
    @Override
    public boolean usePoints(String userId, int amount) {
        int currentPoints = userMapper.getUserPoints(userId);
        if (currentPoints < amount) {
            throw new IllegalArgumentException("포인트가 부족합니다.");
        }
        userMapper.updateUserPoints(userId, -amount);
        PointHistoryVo history = new PointHistoryVo(userId, -amount, "사용", new Date());
        pointMapper.insertPointHistory(history);
        updateUserGrade(userId);
        return true;
    }
    
    private void updateUserGrade(String userId) {
        int totalUsedPoints = pointMapper.getTotalUsedPoints(userId);
        String newGrade = "일반";
        if (totalUsedPoints >= 3000000) {
            newGrade = "VVIP";
        } else if (totalUsedPoints >= 1000000) {
            newGrade = "VIP";
        } else if (totalUsedPoints >= 300000) {
            newGrade = "Silver";
        }
        userMapper.updateUserGrade(userId, newGrade);
    }
}