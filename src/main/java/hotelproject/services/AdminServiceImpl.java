package hotelproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hotelproject.mappers.UserMapper;
import hotelproject.repositories.vo.UserVo;

@Service
public class AdminServiceImpl implements AdminService {

	 @Autowired
	    private UserMapper userMapper;

	    // 유저 목록 조회
	    @Override
	    public List<UserVo> getAllUsers() {
	        return userMapper.getAllUsers();
	    }

	    // 유저 정보 수정
	    @Override
	    @Transactional
	    public boolean updateUser(int id, UserVo user) {
	        return userMapper.updateUser(id, user) > 0;
	    }

	    // 유저 포인트 지급
	    @Override
	    @Transactional
	    public boolean giveUserPoints(int id, int amount) {
	        return userMapper.updateUserPoints(id, amount) > 0;
	    }

	    // 유저 포인트 차감
	    @Override
	    @Transactional
	    public boolean deductUserPoints(int id, int amount) {
	        return userMapper.updateUserPoints(id, -amount) > 0;
	    }

	    // 유저 등급 변경
	    @Override
	    @Transactional
	    public boolean updateUserGrade(int id, int grade) {
	        return userMapper.updateUserGrade(id, grade) > 0;
	    }
}
