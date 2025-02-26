package hotelproject.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import hotelproject.repositories.vo.RoomVo;

@Mapper
public interface RoomMapper {

    // 특정 호텔의 객실 리스트 조회
    List<RoomVo> getRoomsByHotelId(@Param("hotelId") int hotelId);

    // 특정 객실 정보 조회
    RoomVo getRoomById(@Param("roomId") int roomId);
}
