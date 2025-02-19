package hotelproject.mappers;

import org.apache.ibatis.annotations.*;
import java.util.List;
import hotelproject.repositories.vo.ReservationVo;

@Mapper
public interface ReservationMapper {
	
	//	1. 예약등록(추가)
    @Insert("INSERT INTO reservation (user_no, hotel_no, room_no, check_in_date, check_out_date, guest_count, total_price, payment_status, reservation_status) " +
            "VALUES (#{userNo}, #{hotelNo}, #{roomNo}, #{checkInDate}, #{checkOutDate}, #{guestCount}, #{totalPrice}, #{paymentStatus}, #{reservationStatus})")
    @Options(useGeneratedKeys = true, keyProperty = "reservationNo")
    int insertReservation(ReservationVo reservation);

    //	2. 예약등록
    @Select("SELECT * FROM reservation WHERE reservation_no = #{reservationNo}")
    ReservationVo selectReservationById(int reservationNo);

    //	3. 예약 목록조회
    @Select("SELECT * FROM reservation")
    List<ReservationVo> selectAllReservations();

    //	4. 예약수정
    @Update("UPDATE reservation SET user_no = #{userNo}, hotel_no = #{hotelNo}, room_no = #{roomNo}, check_in_date = #{checkInDate}, " +
            "check_out_date = #{checkOutDate}, guest_count = #{guestCount}, total_price = #{totalPrice}, " +
            "payment_status = #{paymentStatus}, reservation_status = #{reservationStatus} WHERE reservation_no = #{reservationNo}")
    int updateReservation(ReservationVo reservation);

    //	5. 예약취소(삭제)	
    @Delete("DELETE FROM reservation WHERE reservation_no = #{reservationNo}")
    int deleteReservation(int reservationNo);
    
    // 6. history 
    @Select("SELECT * FROM reservation WHERE user_no = (SELECT user_no FROM user WHERE email = #{email})")
    List<ReservationVo> selectReservationsByEmail(String email);
}
