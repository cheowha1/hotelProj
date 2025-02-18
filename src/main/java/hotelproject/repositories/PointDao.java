package hotelproject.repositories;

import java.util.List;

import hotelproject.repositories.vo.PointVo;

public interface PointDao {
	void insertPoint(PointVo point);	//	포인트 적립 및 사용 기록추가
	int getUserTotalPoints(int userNo);	//	회원의 총 포인트 조회
	List<PointVo> getUserPointHistory(int userNo);	//	회원의 포인트 내역조회

}
