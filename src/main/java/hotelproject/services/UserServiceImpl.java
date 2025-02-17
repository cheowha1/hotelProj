import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hotelproject.mappers.UserMapper;
import hotelproject.repositories.UserDao;
import hotelproject.repositories.vo.UserVo;

@Service
public class UserServiceImpl implements UserService	{
	
    @Autowired
	private UserDao userDao;

	@Override
	public void chargePoint(String id, int point) {
		userDao.chargePoint(id, point);
	}

	@Autowired
	private UserMapper userMapper;
    
    @Override
    public boolean isEmailAvailable(String email) {
        return userMapper.checkEmail(email) == 0;
    }

    @Override
    public boolean isSsnAvailable(String ssn) {
        return userMapper.checkSsn(ssn) == 0;
    }

    @Override
    public boolean isPhoneAvailable(String phone) {
        return userMapper.checkPhone(phone) == 0;
    }
    
    @Override
    public boolean isNicknameAvailable(String nickname) {
        return userMapper.checkNickname(nickname) == 0;
    }

    @Override
    public boolean isReferenceValid(String reference) {
        return userMapper.checkReference(reference) > 0;
    }
    
    @Override
    public ResponseEntity<String> registerUser(UserVo userVo) {
        userMapper.insertUser(userVo);
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }
    
    @Override
    public ResponseEntity<String> authenticateUser(UserVo loginVo) {
        UserVo user = userMapper.getUserByEmail(loginVo.getEmail());
        if (user != null && user.getPassword().equals(loginVo.getPassword())) {
            return ResponseEntity.ok("로그인 성공");
        }
        return ResponseEntity.badRequest().body("이메일 또는 비밀번호가 올바르지 않습니다.");
    }