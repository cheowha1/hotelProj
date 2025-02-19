package hotelproject.mappers;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import hotelproject.repositories.vo.UserVo;

@Mapper
public interface UserMapper {
	
	 // 모든 회원 조회
    @Select("SELECT * FROM user")
    List<UserVo> selectAllUsers();
    
    // 회원 등급 변경
    @Update("UPDATE user SET grade = #{grade} WHERE user_no = #{userId}")
    void updateUserGrade(@Param("userId") int userId, @Param("grade") String grade);
    
    // 회원 포인트 업데이트 (지급/차감 공통 사용)
    @Update("UPDATE user SET point = CASE WHEN (point + #{point}) < 0 THEN 0 ELSE (point + #{point}) END WHERE user_no = #{userId}")
    void updateUserPoint(@Param("userId") int userId, @Param("point") int point);

    // 기본 포인트 업데이트
    @Update("UPDATE system_settings SET default_point = #{defaultPoint} WHERE setting_id = 1")
    void updateDefaultPoint(@Param("defaultPoint") int defaultPoint);

    // 현재 기본 포인트 값 조회
    @Select("SELECT default_point FROM system_settings WHERE setting_id = 1")
    int getDefaultPoint();

    // 이메일로 사용자 조회
    UserVo findByEmail(@Param("email") String email);
    
    // 중복 체크: 이메일
    int existsByEmail(@Param("email") String email);
    
    // 중복 체크: 닉네임
    int existsByNickname(@Param("nickname") String nickname);
    
    // 중복 체크: 주민번호
    int existsBySsn(@Param("ssn") String ssn);
    
    // 중복 체크: 전화번호
    int existsByPhone(@Param("phone") String phone);
    
    // 회원 저장
    int saveUser(UserVo userVo);
    
//    <포인트 기능 추가>
    // 회원의 포인트 사용내역 조회
   List<Map<String, Object>> getUserPointHistory(int userNo);
   void chaergePoint(@Param("userNo") int userNo, @Param("amount") int amount);

   // 포인트 적립 (+= 연산)
   void earnPoints(@Param("userNo") int userNo, @Param("amount") int amount);

    // 포인트 사용(=결제) (-= 연산, 포인트 부족 시 업데이트 안 함)
   int usePoints(@Param("userNo") int userNo, @Param("amount") int amount);
 
}
