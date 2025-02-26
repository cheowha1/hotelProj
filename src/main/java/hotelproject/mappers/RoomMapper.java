package hotelproject.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import hotelproject.repositories.vo.RoomVo;

@Mapper
public interface RoomMapper {
    // 특정 호텔의 객실 리스트 조회 (기존 @Select 방식 유지)
    @Select("SELECT id, hotel_id, room_name, room_price FROM rooms WHERE hotel_id = #{hotelId}")
    List<RoomVo> getRoomsByHotelId(@Param("hotelId") int hotelId);

    // 특정 객실 정보 조회 (추가)
    @Select("SELECT id, hotel_id, room_name, room_price FROM rooms WHERE id = #{roomId}")
    RoomVo getRoomById(@Param("roomId") int roomId);
    
    //  특정 객실의 이미지 리스트 조회 (객실 ID 기준)
    @Select("SELECT image_url FROM room_images WHERE room_id = #{roomId}")
    List<String> getRoomImages(@Param("roomId") int roomId);
}
