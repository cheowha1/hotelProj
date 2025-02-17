package hotelproject.services;

import org.springframework.http.ResponseEntity;

import hotelproject.repositories.vo.UserVo;

public interface UserService {
	  // 이메일, 주민번호, 전화번호, 닉네임 중복 체크
    boolean isEmailAvailable(String email);
    boolean isSsnAvailable(String ssn);
    boolean isPhoneAvailable(String phone);
    boolean isNicknameAvailable(String nickname);
    
    // 추천인 유효성 체크
    boolean isReferrerValid(String referrer);
    
    // 회원가입
    ResponseEntity<String> registerUser(UserVo userVo);
    
    // 로그인
    ResponseEntity<String> authenticateUser(UserVo loginVo);
    
    void chargePoint(String id, int point);


}
