package hotelproject.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import hotelproject.repositories.vo.ReservationVo;

@Mapper
public interface ReservationMapper {
	
	  void insertReservation(ReservationVo reservation);
	  ReservationVo getReservationById(@Param("reservationId") int reservationId);
	  void deleteReservation(@Param("reservationId") int reservationId);
	  List<ReservationVo> getUserReservations(@Param("userId") String userId);
}
