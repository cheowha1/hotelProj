package hotelproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import hotelproject.mappers.UserMapper;
import hotelproject.repositories.vo.UserVo;

@Service
public class UserServiceImpl implements UserService {

	 @Autowired
	    private UserMapper userMapper;
	    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	    @Override
	    public boolean registerUser(UserVo user) {
	        // 중복 체크
	        if (userMapper.checkDuplicateId(user.getId()) > 0 ||
	            userMapper.checkDuplicateNickname(user.getNickname()) > 0 ||
	            userMapper.checkDuplicatePhone(user.getPhone()) > 0 ||
	            userMapper.checkDuplicateSsn(user.getSsn()) > 0) {
	            throw new IllegalArgumentException("중복된 정보가 있습니다.");
	        }
	        
	        // 주민번호 마스킹화
	        user.setSsn(user.getSsn().substring(0, 6) + "-*******");
	        
	        // 비밀번호 암호화
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	        
	        // 기본 정보 설정
	        user.setGrade("일반");
	        user.setPoint(1000);
	        
	        // 회원가입 진행
	        boolean isRegistered = userMapper.insertUser(user);
	        
	        // 추천인 포인트 지급
	        if (user.getReference() != null && !user.getReference().isEmpty()) {
	            UserVo referrer = userMapper.getUserByNickname(user.getReference());
	            if (referrer != null) {
	                userMapper.updateUserPoints(referrer.getId(), 3000);
	            }
	        }
	        
	        return isRegistered;
	    }
	
	    @Override
	    public UserVo loginUser(String id, String password) {
	        UserVo user = userMapper.getUserById(id); // 🔥 올바른 메서드 사용
	        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
	            throw new IllegalArgumentException("로그인 실패: 아이디 또는 비밀번호가 올바르지 않습니다.");
	        }
	        return user;
	    }
}
