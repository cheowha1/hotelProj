package hotelproject.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hotelproject.controllers.UpdateUserGradeRequest;
import hotelproject.controllers.UpdateUserPointRequest;
import hotelproject.mappers.PointMapper;
import hotelproject.mappers.UserMapper;
import hotelproject.repositories.vo.PointHistoryVo;
import hotelproject.repositories.vo.UserVo;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
    private UserMapper userMapper;
    @Autowired
    private PointMapper pointMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public boolean updateUser(String id, UserVo user) {
        return userMapper.updateUser(id,
                passwordEncoder.encode(user.getPassword()),
                user.getName(),
                user.getNickname(),
                user.getSsn(),
                user.getPhone());
    }

    @Override
    @Transactional
    public boolean modifyUserPoints(String id, UpdateUserPointRequest request) {
        userMapper.updateUserPoints(id, request.amount());
        PointHistoryVo history = new PointHistoryVo(id, request.amount(), request.type(), LocalDateTime.now());
        pointMapper.insertPointHistory(history.getUserId(), history.getAmount(), history.getType());
        return true;
    }

    @Override
    public boolean updateUserGrade(String id, UpdateUserGradeRequest request) {
        return userMapper.updateUserGrade(id, request.grade());
    }
}
