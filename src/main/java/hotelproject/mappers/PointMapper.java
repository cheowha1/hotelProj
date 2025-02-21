package hotelproject.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import hotelproject.repositories.vo.PointVo;

@Mapper
public class PointMapper {

    // 포인트 사용 기록 추가 (예약 시)
    @Insert("INSERT INTO points (user_no, point_amount, point_type, created_at) VALUES (#{userNo}, #{pointAmount}, 'RESERVATION', NOW())")
    void insertReservationPoint(PointVo point);

    // 회원의 총 포인트 조회
    @Select("SELECT COALESCE(SUM(point_amount), 0) FROM points WHERE user_no = #{userNo}")
    int getUserTotalPoints(@Param("userNo") int userNo);

    // 포인트 사용 내역 조회
    @Select("SELECT * FROM points WHERE user_no = #{userNo} ORDER BY created_at DESC")
    List<PointVo> getPointHistory(@Param("userNo") int userNo);
}
