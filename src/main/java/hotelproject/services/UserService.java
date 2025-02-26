package hotelproject.services;

import hotelproject.repositories.vo.UserVo;
import jakarta.servlet.http.HttpSession;

public interface UserService {
	  boolean registerUser(UserVo user);
	// 로그인
	    UserVo loginUser(String id, String password, HttpSession session);
	    
	    // 마이페이지 정보 가져오기 (세션 기반)
	    UserVo getUserFromSession(HttpSession session);
	    void chargePoints(int amount, HttpSession session);
}
