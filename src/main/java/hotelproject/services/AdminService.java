package hotelproject.services;

import java.util.List;

import hotelproject.repositories.vo.UserVo;

public interface AdminService {
	// 유저 목록 조회
    List<UserVo> getAllUsers();

    // 유저 정보 수정
    boolean updateUser(int id, UserVo user);

    // 유저 포인트 지급
    boolean giveUserPoints(int id, int amount);

    // 유저 포인트 차감
    boolean deductUserPoints(int id, int amount);

    // 유저 등급 변경
    boolean updateUserGrade(int id, int grade);
}