package hotelproject.services;
import java.util.List;
import java.util.Map;

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
    
    // 포인트 충전
    void chargePoint(String id, int point);
    
    // <포인트 기능 추가>
    // 포인트 결제 ( 사용 성공 여부 반환)
    boolean usePoints(String id, int amount);
    
    // 회원 포인트 사용내역 조회
    List<Map<String, Object>> getUserPointHistory(int UserNo);
    void chargePoint(int userNo, int amount);
    
    // 포인트 적립
    void earnPoints(int UserNo, int amount);
    
    // 회원가입 시 기본 포인트 적립(신규회원대상)
    ResponseEntity<String> registerUserWithInitialPoints(UserVo userVo, int intialPoints);
    
    
}
