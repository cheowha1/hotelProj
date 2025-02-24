package hotelproject.mappers;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import hotelproject.repositories.vo.UserVo;

@Mapper
public interface UserMapper {
	 int checkDuplicateId(@Param("id") String id);
	    int checkDuplicateNickname(@Param("nickname") String nickname);
	    int checkDuplicatePhone(@Param("phone") String phone);
	    int checkDuplicateSsn(@Param("ssn") String ssn);
	    boolean insertUser(UserVo user);
	    UserVo getUserByNickname(@Param("nickname") String nickname);
	    boolean updateUser(@Param("userId") int userId, @Param("user") UserVo user);
	    UserVo getUserById(@Param("id") String id);
	    boolean updateUserPoints(@Param("id") String id, @Param("points") int points);
	    int getUserPoints(@Param("id") String id);
	    boolean updateUserGrade(@Param("id") String id, @Param("grade") String grade);
	    boolean updateUser(@Param("id") String id, @Param("user") UserVo user);
}
