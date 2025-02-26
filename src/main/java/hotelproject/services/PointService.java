package hotelproject.services;

import java.util.List;

import hotelproject.repositories.vo.PointHistoryVo;

public interface PointService {
   
	// 포인트 충전
    void chargePoints(int amount, String userId);

    // 포인트 사용
    void usePoints(String userId, int amount);

    // 포인트 내역 조회
    List<PointHistoryVo> getPointHistory(String userId);

    // 포인트 사용에 따른 등급 업데이트
    void updateUserGrade(String userId);
}
