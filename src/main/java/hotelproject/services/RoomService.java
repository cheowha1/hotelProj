package hotelproject.services;

import java.util.List;

import hotelproject.repositories.vo.RoomVo;

public interface RoomService {
    List<RoomVo> getRoomsByHotelId(int hotelId);
    RoomVo getRoomById(int roomId);
}
