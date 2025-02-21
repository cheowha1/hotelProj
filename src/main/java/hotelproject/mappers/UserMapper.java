package hotelproject.mappers;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import hotelproject.repositories.vo.UserVo;

@Mapper
public interface UserMapper {
	
	// 회원가입 (포인트 1000 지급 포함, 추천인 설정 가능)
    @Insert("INSERT INTO users (id, password, name, nickname, ssn, phone, address, marketing_opt_in, grade, point, role, reference) " +
            "VALUES (#{id}, #{password}, #{name}, #{nickname}, #{ssn}, #{phone}, #{address}, #{marketingOptIn}, 1, 1000, 'USER', #{reference})")
    @Options(useGeneratedKeys = true, keyProperty = "no")
    boolean insertUser(UserVo user);

    // 로그인 (ID(이메일)과 비밀번호로 유저 조회)
    @Select("SELECT * FROM users WHERE id = #{id} AND password = #{password}")
    UserVo getUserByIdAndPassword(@Param("id") String id, @Param("password") String password);

    // 유저 정보 조회
    @Select("SELECT * FROM users WHERE no = #{userNo}")
    UserVo getUserByNo(@Param("userNo") int userNo);

    // ID(이메일) 중복 체크
    @Select("SELECT COUNT(*) FROM users WHERE id = #{id}")
    int checkDuplicateId(@Param("id") String id);

    // 닉네임 중복 체크
    @Select("SELECT COUNT(*) FROM users WHERE nickname = #{nickname}")
    int checkDuplicateNickname(@Param("nickname") String nickname);

    // 주민번호 중복 체크
    @Select("SELECT COUNT(*) FROM users WHERE ssn = #{ssn}")
    int checkDuplicateSsn(@Param("ssn") String ssn);

    // 전화번호 중복 체크
    @Select("SELECT COUNT(*) FROM users WHERE phone = #{phone}")
    int checkDuplicatePhone(@Param("phone") String phone);
 
}
