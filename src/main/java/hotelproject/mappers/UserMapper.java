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
}
