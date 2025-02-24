package hotelproject.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public boolean updateUser(String id, UserVo user) {
        return userMapper.updateUser(id, user);
    }

    @Override
    public boolean modifyUserPoints(String id, int amount, String type) {
        userMapper.updateUserPoints(id, amount);
        PointHistoryVo history = new PointHistoryVo(id, amount, type, new Date());
        pointMapper.insertPointHistory(history);
        return true;
    }

    @Override
    public boolean updateUserGrade(String id, String grade) {
        return userMapper.updateUserGrade(id, grade);
    }
}
