package hotelproject.mappers;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import hotelproject.repositories.vo.UserVo;

@Mapper
public interface UserMapper {

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
    // 회원의 총 포인트 조회
   int getUserTotalPoints(@Param("userNo") int userNo);

   // 포인트 적립 (+= 연산)
   void earnPoints(@Param("userNo") int userNo, @Param("amount") int amount);

    // 포인트 사용 (-= 연산, 포인트 부족 시 업데이트 안 함)
   int usePoints(@Param("userNo") int userNo, @Param("amount") int amount);
 
}
