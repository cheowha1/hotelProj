package hotelproject.services;

import java.util.HashMap;
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
    public ResponseEntity<String> authenticateUser(UserVo loginVo) { 
        // 1. 이메일을 기준으로 사용자 조회
        UserVo foundUser = userMapper.findByEmail(loginVo.getEmail());
        if (foundUser == null) {
            return ResponseEntity.badRequest().body("이메일 또는 비밀번호가 올바르지 않습니다.");
        }

        // 2. 비밀번호 검증 (암호화된 비밀번호와 입력된 비밀번호 비교)
        if (!passwordEncoder.matches(loginVo.getPassword(), foundUser.getPassword())) {
            return ResponseEntity.badRequest().body("이메일 또는 비밀번호가 올바르지 않습니다.");
        }

        // 3. 로그인 성공 → 사용자 정보 반환 (닉네임, 등급, 포인트 포함)
        Map<String, Object> response = new HashMap<>();
        response.put("message", "로그인에 성공했습니다.");
        response.put("nickname", foundUser.getNickname());
        response.put("grade", foundUser.getGrade()); // 등급 추가 (admin인지 확인 가능)
        response.put("point", foundUser.getPoint());

        return ResponseEntity.ok(response);
    }
    
    public UserVo getUserByToken(String token) {
        // 토큰을 이용해 사용자 이메일 가져오기 (JWT 또는 세션 방식에 따라 구현)
        String email = jwtUtil.getEmailFromToken(token);

        if (email == null) {
            return null;
        }

        // 이메일을 기준으로 사용자 조회
        return userMapper.findByEmail(email);
    }
    
    public UserVo getUserByEmail(String email) {
        return userMapper.findByEmail(email);
    }
    
    @Override
    public void chargePoint(String id, int point) {
        // 포인트 충전 로직 (추후 구현)
    }
}
