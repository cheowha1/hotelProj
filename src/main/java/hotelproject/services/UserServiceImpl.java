package hotelproject.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hotelproject.mappers.UserMapper;
import hotelproject.repositories.vo.UserVo;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Override
    public UserVo getUserByEmail(String email) {
        return userMapper.findByEmail(email);
    }

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
            
            // 신규 가입자에게 1000포인트 지급
            userMapper.earnPoints(userVo.getId(), 1000);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("회원가입 중 오류가 발생했습니다.");
        }
        return ResponseEntity.ok("회원가입에 성공했습니다. 기본 1000포인트가 지급되었습니다.");
    }
    
    @Override
    public UserVo authenticateUser(String email, String password) { 
        UserVo foundUser = userMapper.findByEmail(email);
        if (foundUser == null || !passwordEncoder.matches(password, foundUser.getPassword())) {
            return null; // 로그인 실패
        }
        return foundUser; // ✅ UserVo 객체 반환 (세션 저장은 컨트롤러에서 처리)
    }

    
    @Override
    @Transactional
    public boolean chargePoints(int userNo, int amount) {
        int bonus = (int) (amount * 0.1);
        int totalAmount = amount + bonus;
        int updatedRows = userMapper.chargeUserPoints(userNo, totalAmount);
        return updatedRows > 0;
    }
    
    @Override
    @Transactional
    public boolean usePoints(int userNo, int amount) {
        int updatedRows = userMapper.usePoints(userNo, amount);
        return updatedRows > 0;
    }
    
    @Override
    public List<Map<String, Object>> getUserPointHistory(int userNo) {
        return userMapper.getUserPointHistory(userNo);
    }
}
