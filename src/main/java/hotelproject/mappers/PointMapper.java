package hotelproject.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import hotelproject.repositories.vo.PointHistoryVo;

@Mapper
public interface PointMapper {

	 // 포인트 충전 내역 기록
	@Insert(
            "INSERT INTO points (user_id, amount, type, created_at) " +
                    "VALUES (#{userId}, #{amount}, #{type}, NOW())"
    )
    void insertPointHistory(@Param("userId") String userId,
                            @Param("amount") int amount,
                            @Param("type") String type);

   

    // 특정 유저의 포인트 업데이트
    void updateUserPoints(@Param("userId") String userId, @Param("points") int points);

    // 포인트 내역 조회
    List<PointHistoryVo> getPointHistory(@Param("userId") String userId);

    // 포인트 충전 내역 조회 (관리자용)
    List<PointHistoryVo> getAllPointHistory();
    
}
