package hotelproject.services;

import java.util.List;

import hotelproject.repositories.vo.UserVo;

public interface AdminService {
    List<UserVo> getAllUsers(); // 모든 회원 조회
    void updateUserGrade(int userId, String grade); // 회원 등급 변경
    void chargePoint(int userId, int point); // 포인트 지급
    void deductPoint(int userId, int point); // 포인트 차감
    void updatePointSettings(int defaultPoint); // 기본 포인트 정책 변경
}