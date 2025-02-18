package hotelproject.services;

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
        UserVo foundUser = userMapper.findByEmail(loginVo.getEmail());
        if (foundUser == null) {
            return ResponseEntity.badRequest().body("사용자를 찾을 수 없습니다.");
        }
        if (!passwordEncoder.matches(loginVo.getPassword(), foundUser.getPassword())) {
            return ResponseEntity.badRequest().body("비밀번호가 일치하지 않습니다.");
        }
        return ResponseEntity.ok("로그인에 성공했습니다.");
    }
    
    @Override
    public void chargePoint(String id, int point) {
        // 포인트 충전 로직 (추후 구현)
    }
}
