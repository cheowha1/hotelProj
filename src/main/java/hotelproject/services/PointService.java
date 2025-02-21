package hotelproject.services;

import java.util.List;

import hotelproject.repositories.vo.PointVo;

public interface PointService {
    // 포인트 사용하여 호텔 예약
    boolean usePointsForReservation(int userNo, int amount);

    // 포인트 사용 내역 조회 (히스토리)
    List<PointVo> getPointHistory(int userNo);

    // 현재 유저의 포인트 조회
    int getUserTotalPoints(int userNo);
}
