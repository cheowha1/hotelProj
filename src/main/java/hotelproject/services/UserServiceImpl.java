package hotelproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import hotelproject.mappers.UserMapper;
import hotelproject.repositories.vo.UserVo;
import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public boolean registerUser(UserVo user) {
        // 중복 체크
        if (userMapper.checkDuplicateId(user.getId()) > 0) {
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
        }
        if (userMapper.checkDuplicateNickname(user.getNickname()) > 0) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
        }
        if (userMapper.checkDuplicatePhone(user.getPhone()) > 0) {
            throw new IllegalArgumentException("이미 사용 중인 전화번호입니다.");
        }
        if (userMapper.checkDuplicateSsn(user.getSsn()) > 0) {
            throw new IllegalArgumentException("이미 등록된 주민번호입니다.");
        }

        // 주민번호 마스킹화 및 비밀번호 암호화
        user.setSsn(user.getSsn().substring(0, 6) + "-*******");
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 기본 정보 설정
        user.setGrade("일반");
        user.setPoint(1000);
        user.setRole("USER");

        // 추천인 처리 (추천인 ID 설정)
        if (user.getReference() != null && !user.getReference().isEmpty()) {
            UserVo referrer = userMapper.getUserByNickname(user.getReference());
            if (referrer != null) {
                user.setReference(referrer.getId()); // 추천인 ID 설정
            } else {
                user.setReference(null); // 추천인이 없으면 NULL로 설정
            }
        }

        // 회원가입 진행
        int result = userMapper.insertUser(user);

        // 추천인 포인트 지급
        if (user.getReference() != null) {
            userMapper.updateUserPoints(user.getReference(), 3000);
        }

        return result > 0; // 삽입된 행이 1개 이상이면 true 반환
    }

    // 로그인 시 세션에 ID 저장
    @Override
    public UserVo loginUser(String id, String password, HttpSession session) {
        UserVo user = userMapper.getUserById(id);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다.");
        }
        session.setAttribute("userId", id);  // 로그인된 ID 세션에 저장
        return user;
    }

    // 마이페이지에서 세션을 이용하여 정보 가져오기
    public UserVo getUserFromSession(HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            throw new IllegalArgumentException("로그인 필요");
        }
        return userMapper.getUserById(userId);  // 세션에서 ID를 사용해 사용자 정보 가져오기
    }
    
    @Override
    public void chargePoints(int amount, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }
        
        // 사용자 정보 조회
        UserVo user = userMapper.getUserById(userId);
        if (user == null) {
            throw new IllegalArgumentException("유저 정보가 없습니다.");
        }

        // 포인트 충전 처리
        userMapper.updateUserPoints(userId, user.getPoint() + amount); // 포인트 업데이트
        userMapper.insertPointHistory(userId, amount, "충전"); // 충전 내역 저장

        // 포인트에 따른 등급 자동 변경
        updateUserGrade(userId);
    }

    // 등급 변경 로직
    private void updateUserGrade(String userId) {
        UserVo user = userMapper.getUserById(userId);
        int totalUsedPoint = user.getTotalUsedPoint();  // 포인트 사용량
        String grade = "일반";  // 기본 등급

        // 포인트 사용량에 따라 등급 변경
        if (totalUsedPoint >= 10000) {
            grade = "Silver";
        } else if (totalUsedPoint >= 50000) {
            grade = "VIP";
        } else if (totalUsedPoint >= 100000) {
            grade = "VVIP";
        }

        // 등급 업데이트
        userMapper.updateUserGrade(userId, grade);
    }
}
