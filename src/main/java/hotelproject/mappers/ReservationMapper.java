package hotelproject.mappers;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import hotelproject.repositories.vo.ReservationVo;

@Mapper
public interface ReservationMapper {
	
	 @Insert("INSERT INTO reservations (user_id, hotel_id, room_id, room_name, check_in_date, check_out_date, adult_count, child_count, total_price, reservation_status) VALUES (#{userId}, #{hotelId}, #{roomId}, #{roomName}, #{checkInDate}, #{checkOutDate}, #{adultCount}, #{childCount}, #{totalPrice}, '예약 중')")
	    void insertReservation(ReservationVo reservation);
	 @Select("SELECT * FROM reservations WHERE user_id = #{userId}")
	    List<ReservationVo> getReservationsByUserId(String userId);
	 
	 @Select("SELECT COUNT(*) FROM reservations " +
		        "WHERE user_id = #{userId} " +
		        "AND room_id = #{roomId} " +
		        "AND check_in_date = #{checkInDate} " +
		        "AND check_out_date = #{checkOutDate}")
		int checkExistingReservation(@Param("userId") String userId,
		                             @Param("roomId") int roomId,
		                             @Param("checkInDate") String string,
		                             @Param("checkOutDate") String string2);

	 
//	  ReservationVo getReservationById(@Param("reservationId") int reservationId);
//	  void deleteReservation(@Param("reservationId") int reservationId);
//	  List<ReservationVo> getUserReservations(@Param("userId") String userId);
}
