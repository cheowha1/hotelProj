package hotelproject.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
	void chargePoint(@Param("id") String id, @Param("point") int point);

}
