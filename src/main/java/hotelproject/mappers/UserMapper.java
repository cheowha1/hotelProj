package hotelproject.mappers;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import hotelproject.repositories.vo.UserVo;

@Mapper
public interface UserMapper {
		@Select("SELECT COUNT(*) FROM users WHERE id = #{id}")
	    int checkDuplicateId(@Param("id") String id);
		
		@Select("SELECT COUNT(*) FROM users WHERE nickname = #{nickname}")
	    int checkDuplicateNickname(@Param("nickname") String nickname);

	    @Select("SELECT COUNT(*) FROM users WHERE phone = #{phone}")
	    int checkDuplicatePhone(@Param("phone") String phone);

	    @Select("SELECT COUNT(*) FROM users WHERE ssn = #{ssn}")
	    int checkDuplicateSsn(@Param("ssn") String ssn);

	    @Insert("INSERT INTO users (id, password, name, nickname, ssn, phone, reference, grade, point, role) " +
	            "VALUES (#{id}, #{password}, #{name}, #{nickname}, #{ssn}, #{phone}, #{reference}, #{grade}, #{point}, #{role})")
	    @Options(useGeneratedKeys = true, keyProperty = "no")
	    int insertUser(UserVo user);
	    UserVo getUserByNickname(@Param("nickname") String nickname);
	    boolean updateUser(@Param("userId") int userId, @Param("user") UserVo user);
	    @Select("SELECT id, password, name, nickname, ssn, phone, grade, point FROM users WHERE id = #{id}")
	    UserVo getUserById(@Param("id") String id);
	    int getUserPoints(@Param("id") String id);
	    boolean updateUser(@Param("id") String id, @Param("user") UserVo user);
	    void updateUserPoints(@Param("userId") String userId, @Param("points") int points);
	    
	    void insertPointHistory(@Param("userId") String userId, @Param("amount") int amount, @Param("type") String type);
	    
	    void updateUserGrade(@Param("userId") String userId, @Param("grade") String grade);
}
