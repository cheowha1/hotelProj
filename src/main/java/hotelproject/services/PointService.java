package hotelproject.services;

import java.util.List;

import hotelproject.repositories.vo.PointVo;

public interface PointService {
	void earnPoints(int userNo, int amount);	//	포인트 적립 (회원번호 , 포인트금액)
	boolean usePoints(int userNo, int amount);	//	포인트 사용기능(가지고 있는 포인트만큼)
	int getUserTotalPoints(int userNo);			//	총포인트 초회
	List<PointVo> getUserPointHistory(int userNo);	//	포인트 내역조회

}
