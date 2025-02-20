package hotelproject.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hotelproject.mappers.UserMapper;
import hotelproject.repositories.vo.UserVo;
import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    private String maskSsn(String ssn) {
        if (ssn == null || ssn.length() != 14 || ssn.charAt(6) != '-') {
            throw new IllegalArgumentException("올바른 주민번호 형식이 아닙니다.");
        }
        return ssn.substring(0, 8) + "******";
    }

    @Override
    public boolean isEmailAvailable(String email) {
        return userMapper.existsByEmail(email) == 0;
    }
    
    @Override
    public boolean isNicknameAvailable(String nickname) {
        return userMapper.existsByNickname(nickname) == 0;
    }
    
    @Override
    public boolean isSsnAvailable(String ssn) {
        return userMapper.existsBySsn(ssn) == 0;
    }
    
    @Override
    public boolean isPhoneAvailable(String phone) {
        return userMapper.existsByPhone(phone) == 0;
    }
    
    @Override
    public boolean isReferrerValid(String referrer) {
        // 추천인 유효성 체크 (닉네임 존재 여부)
        return userMapper.existsByNickname(referrer) > 0;
    }
    
    @Override
    @Transactional
    public ResponseEntity<String> registerUser(UserVo userVo) {
        // 중복 체크
        if (!isEmailAvailable(userVo.getEmail())) {
            return ResponseEntity.badRequest().body("이미 사용 중인 이메일입니다.");
        }
        if (!isNicknameAvailable(userVo.getNickname())) {
            return ResponseEntity.badRequest().body("이미 사용 중인 닉네임입니다.");
        }
        if (!isSsnAvailable(userVo.getSsn())) {
            return ResponseEntity.badRequest().body("이미 사용 중인 주민번호입니다.");
        }
        if (!isPhoneAvailable(userVo.getPhone())) {
            return ResponseEntity.badRequest().body("이미 사용 중인 전화번호입니다.");
        }
        
        // 주민번호 마스킹 적용
        userVo.setSsn(maskSsn(userVo.getSsn()));
        
        // 비밀번호 암호화 처리
        String rawPassword = userVo.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        userVo.setPassword(encodedPassword);
        
        // 기본 회원 등급 설정 (없을 경우 "일반")
        if (userVo.getGrade() == null || userVo.getGrade().trim().isEmpty()) {
            userVo.setGrade("일반");
        }
        
        try {
            userMapper.saveUser(userVo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("회원가입 중 오류가 발생했습니다.");
        }
        return ResponseEntity.ok("회원가입에 성공했습니다.");
    }
    
    @Override
    public ResponseEntity<String> authenticateUser(UserVo loginVo, HttpSession session) { 
        // 1. 이메일을 기준으로 사용자 조회
        UserVo foundUser = userMapper.findByEmail(loginVo.getEmail());
        if (foundUser == null) {
            return ResponseEntity.badRequest().body("이메일 또는 비밀번호가 올바르지 않습니다.");
        }

        // 2. 비밀번호 검증 (암호화된 비밀번호와 입력된 비밀번호 비교)
        if (!passwordEncoder.matches(loginVo.getPassword(), foundUser.getPassword())) {
            return ResponseEntity.badRequest().body("이메일 또는 비밀번호가 올바르지 않습니다.");
        }

        // 3. 로그인 성공 → 사용자 정보 세션에 저장
        session.setAttribute("loggedInUser", foundUser);
        
        // 4. 사용자 정보 반환 (닉네임, 등급, 포인트 포함)
        Map<String, Object> response = new HashMap<>();
        response.put("message", "로그인에 성공했습니다.");
        response.put("nickname", foundUser.getNickname());
        response.put("grade", foundUser.getGrade()); // 등급 추가 (admin인지 확인 가능)
        response.put("point", foundUser.getPoint());

        return ResponseEntity.ok(response);
    }
    
    public UserVo getUserByEmail(String email) {
        return userMapper.findByEmail(email);
    }
    
    @Override
    public void chargePoint(String id, int point) {
        // 포인트 충전 로직 (추후 구현)
    }
    
    // <포인트 기능 추가>
    //	포인트 적립
    @Transactional
    public void earnPoints(int userNo, int amount) { 
        userMapper.earnPoints(userNo, amount);
    }
    
    // 회원가입 시 기존회원 확인 후 기존회원 시 1000포인트 지급 
    // 단. 기본 회원 아닐 시 포인트지급 없음
    
    @Transactional
    public ResponseEntity<String> registerUserWithInitialPoints(UserVo user) {
        // 중복 체크
        if (!isEmailAvailable(user.getEmail())) {
            return ResponseEntity.badRequest().body("이미 사용 중인 이메일입니다.");
        }
        if (!isNicknameAvailable(user.getNickname())) {
            return ResponseEntity.badRequest().body("이미 사용 중인 닉네임입니다.");
        }
        if (!isSsnAvailable(user.getSsn())) {
            return ResponseEntity.badRequest().body("이미 사용 중인 주민번호입니다.");
        }
        if (!isPhoneAvailable(user.getPhone())) {
            return ResponseEntity.badRequest().body("이미 사용 중인 전화번호입니다.");
        }

        // 주민번호 마스킹 적용
        user.setSsn(maskSsn(user.getSsn()));

        // 비밀번호 암호화 처리
        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);

        try {
            // 신규 회원인지 확인
            UserVo existingUser = userMapper.findByEmail(user.getEmail());
            if (existingUser != null) {
                return ResponseEntity.badRequest().body("이미 가입된 회원입니다.");
            }

            // 회원 ID 가져오기
            int userNo = user.getId();

            // 신규 회원에게만 1000포인트 지급
            userMapper.earnPoints(userNo, 1000);

            return ResponseEntity.ok("회원가입 성공! 기본 1000포인트가 지급되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("회원가입 중 오류가 발생했습니다.");
        }
    }
    
    //	포인트 사용
    @Transactional
    public boolean usePoints(int userNo, int amount) {
    	int updatedRows = userMapper.usePoints(userNo, amount);
    	return updatedRows > 0;	//	업데이트 성공여부 반환
    }
      
    //	회원의 사용포인트 내역 조회
    public List<Map<String, Object>> getUserPointHistory(int userNo) {
        return userMapper.getUserPointHistory(userNo);
    }

    
    //	결제 시 포인트 적립 (결제 금액의 10% 적립)
    @Transactional
    public void addPointsAfterPayment(int userNo, int amount) {
        userMapper.addPointsAfterPayment(userNo, amount);
    }
    
    // 마이페이지에서 포인트 충전
    @Transactional
    public boolean chargePoints(int userNo, int amount) {
        // 결제 금액의 10% 추가 지급
        int bonus = (int) (amount * 0.1);
        int totalAmount = amount + bonus;

        // 포인트 업데이트
        int updatedRows = userMapper.chargeUserPoints(userNo, totalAmount);

        return updatedRows > 0;
    }

    
    
}
