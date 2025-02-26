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

	        // 주민번호 마스킹화
	        user.setSsn(user.getSsn().substring(0, 6) + "-*******");

	        // 비밀번호 암호화
	        user.setPassword(passwordEncoder.encode(user.getPassword()));

	        // 기본 정보 설정
	        user.setGrade("일반");
	        user.setPoint(1000);
	        user.setRole("USER");

	        // 추천인 닉네임이 존재하면 해당 ID 조회 후 설정
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

	        // 추천인 포인트 지급 (유효한 추천인이 있을 경우)
	        if (user.getReference() != null) {
	            userMapper.updateUserPoints(user.getReference(), 3000);
	        }

	        return result > 0; // 삽입된 행이 1개 이상이면 true 반환
	    }

	
	    @Override
	    public UserVo loginUser(String id, String password) {
	        UserVo user = userMapper.getUserById(id);
	        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
	            throw new IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다.");
	        }
	        return user;
	    }
}
