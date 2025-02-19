package hotelproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotelproject.mappers.UserMapper;
import hotelproject.repositories.vo.UserVo;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserVo> getAllUsers() {
        return userMapper.selectAllUsers();
    }

    @Override
    public void updateUserGrade(int userId, String grade) {
        userMapper.updateUserGrade(userId, grade);
    }


    @Override
    public void chargePoint(int userId, int point) {
        userMapper.updateUserPoint(userId, point);
    }

    @Override
    public void deductPoint(int userId, int point) {
        userMapper.updateUserPoint(userId, -point);
    }

    @Override
    public void updatePointSettings(int defaultPoint) {
        userMapper.updateDefaultPoint(defaultPoint);
    }
}
