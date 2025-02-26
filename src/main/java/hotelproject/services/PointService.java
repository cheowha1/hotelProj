package hotelproject.services;

import java.util.List;

import hotelproject.repositories.vo.PointHistoryVo;
import jakarta.servlet.http.HttpSession;

public interface PointService {
   
		void chargePoints(int amount, HttpSession session);
	   boolean usePoints(String userId, int amount);
	   List<PointHistoryVo> getPointHistory(String userId);
	   void updateUserGrade(String userId);
}
