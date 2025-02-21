package hotelproject.services;

import java.util.List;

import hotelproject.repositories.vo.ReviewVo;
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
    
    // 모든 리뷰 조회 (관리자용)
    List<ReviewVo> getAllReviews();

    // 특정 유저의 리뷰 조회 (관리자용)
    List<ReviewVo> getReviewsByUser(int userNo);

    // 특정 호텔의 리뷰 조회 (관리자용)
    List<ReviewVo> getReviewsByHotel(int hotelNo);

    // 관리자 리뷰 삭제
    boolean deleteReviewByAdmin(int reviewNo);
}