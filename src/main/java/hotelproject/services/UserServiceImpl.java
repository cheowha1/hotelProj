package hotelproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hotelproject.mappers.PointMapper;
import hotelproject.mappers.UserMapper;
import hotelproject.repositories.vo.PointVo;
import hotelproject.repositories.vo.UserVo;
import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

	  @Autowired
	    private UserMapper userMapper;

	    @Autowired
	    private PointMapper pointMapper;

	    @Autowired
	    private HttpSession session;

	    // 회원가입 (회원가입 시 포인트 1000 지급, 추천인 있을 경우 추천인에게 3000 지급)
	    @Override
	    @Transactional
	    public String registerUser(UserVo user) {
	        if (isIdDuplicate(user.getId())) {
	            return "이미 사용 중인 이메일(ID)입니다.";
	        }
	        if (isNicknameDuplicate(user.getNickname())) {
	            return "이미 사용 중인 닉네임입니다.";
	        }
	        if (isSsnDuplicate(user.getSsn())) {
	            return "이미 등록된 주민번호입니다.";
	        }
	        if (isPhoneDuplicate(user.getPhone())) {
	            return "이미 사용 중인 전화번호입니다.";
	        }
	        
	        boolean success = userMapper.insertUser(user);
	        if (success) {
	            // 회원가입 시 초기 포인트 지급
	            PointVo signupBonus = new PointVo(user.getNo(), 1000, "SIGNUP_BONUS");
	            pointMapper.insertChargePoint(signupBonus);
	            
	            // 추천인 유효성 검사 후 포인트 지급
	            if (user.getReference() != null) {
	                UserVo referrer = userMapper.getUserByNo(user.getReference());
	                if (referrer != null) {
	                    PointVo referralBonus = new PointVo(user.getReference(), 3000, "REFERRAL_BONUS");
	                    pointMapper.insertChargePoint(referralBonus);
	                }
	            }
	            return "회원가입 성공 (포인트 지급 완료)";
	        }
	        return "회원가입 실패";
	    }

	    // 로그인 (세션 저장)
	    @Override
	    public UserVo loginUser(String id, String password) {
	        UserVo user = userMapper.getUserByIdAndPassword(id, password);
	        if (user != null) {
	            session.setAttribute("userNo", user.getNo());
	            return user;
	        }
	        return null;
	    }

	    // 로그아웃 (세션 삭제)
	    @Override
	    public void logoutUser() {
	        session.invalidate();
	    }

	    // 유저 정보 조회
	    @Override
	    public UserVo getUserInfo(int userNo) {
	        return userMapper.getUserByNo(userNo);
	    }

	    // 유저의 포인트 내역 조회 (PointService와 연동)
	    @Override
	    public List<PointVo> getUserPointHistory(int userNo) {
	        return pointMapper.getPointHistory(userNo);
	    }

	    // 유저 포인트 충전 (PointService와 연동)
	    @Override
	    @Transactional
	    public boolean chargeUserPoints(int userNo, int amount) {
	        PointVo point = new PointVo(userNo, amount, "CHARGE");
	        pointMapper.insertChargePoint(point);
	        return true;
	    }

	    // 중복 체크 메서드들 추가
	    @Override
	    public boolean isIdDuplicate(String id) {
	        return userMapper.checkDuplicateId(id) > 0;
	    }

	    @Override
	    public boolean isNicknameDuplicate(String nickname) {
	        return userMapper.checkDuplicateNickname(nickname) > 0;
	    }

	    @Override
	    public boolean isSsnDuplicate(String ssn) {
	        return userMapper.checkDuplicateSsn(ssn) > 0;
	    }

	    @Override
	    public boolean isPhoneDuplicate(String phone) {
	        return userMapper.checkDuplicatePhone(phone) > 0;
	    }
}
