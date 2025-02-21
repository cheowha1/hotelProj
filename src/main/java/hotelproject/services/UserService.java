package hotelproject.services;
import java.util.List;

import hotelproject.repositories.vo.PointVo;
import hotelproject.repositories.vo.ReviewVo;
import hotelproject.repositories.vo.UserVo;

public interface UserService {
    // 회원가입 (회원가입 시 포인트 1000 지급 + 추천인 3000 지급)
    String registerUser(UserVo user);

    // 로그인 (세션 저장)
    UserVo loginUser(String id, String password);

    // 로그아웃 (세션 삭제)
    void logoutUser();

    // 유저 정보 조회
    UserVo getUserInfo(int userNo);

    // 유저의 포인트 내역 조회 (PointService와 연동)
    List<PointVo> getUserPointHistory(int userNo);

    // 유저 포인트 충전 (PointService와 연동)
    boolean chargeUserPoints(int userNo, int amount);

    // ID(이메일) 중복 체크
    boolean isIdDuplicate(String id);

    // 닉네임 중복 체크
    boolean isNicknameDuplicate(String nickname);

    // 주민번호 중복 체크
    boolean isSsnDuplicate(String ssn);

    // 전화번호 중복 체크
    boolean isPhoneDuplicate(String phone);
    
 // 유저가 작성한 리뷰 조회 (ReviewService와 연동)
    List<ReviewVo> getUserReviews(int userNo);
}
