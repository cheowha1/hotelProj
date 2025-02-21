package hotelproject.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import hotelproject.repositories.vo.ReservationVo;

@Mapper
public interface ReservationMapper {
	
	// 호텔 예약 추가
    @Insert("INSERT INTO reservations (user_no, hotel_no, amount, created_at) VALUES (#{userNo}, #{hotelNo}, #{amount}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "reservationNo")
    void insertReservation(ReservationVo reservation);

    // 유저의 호텔 예약 목록 조회
    @Select("SELECT * FROM reservations WHERE user_no = #{userNo} ORDER BY created_at DESC")
    List<ReservationVo> getUserReservations(@Param("userNo") int userNo);

    // 유저의 호텔 예약 내역 조회 (히스토리)
    @Select("SELECT * FROM reservations WHERE user_no = #{userNo} ORDER BY created_at DESC")
    List<ReservationVo> getReservationHistory(@Param("userNo") int userNo);

    // 특정 예약 정보 조회
    @Select("SELECT * FROM reservations WHERE reservation_no = #{reservationNo}")
    ReservationVo getReservationById(@Param("reservationNo") int reservationNo);

    // 호텔 예약 취소
    @Delete("DELETE FROM reservations WHERE reservation_no = #{reservationNo}")
    void deleteReservation(@Param("reservationNo") int reservationNo);
}
