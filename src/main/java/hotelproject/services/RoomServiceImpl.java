package hotelproject.services;

import java.util.List;

import org.springframework.stereotype.Service;

import hotelproject.mappers.RoomMapper;
import hotelproject.repositories.vo.RoomVo;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomMapper roomMapper;

    public RoomServiceImpl(RoomMapper roomMapper) {
        this.roomMapper = roomMapper;
    }

    // 특정 호텔의 객실 리스트 조회
    @Override
    public List<RoomVo> getRoomsByHotelId(int hotelId) {
        return roomMapper.getRoomsByHotelId(hotelId);
    }

    // 특정 객실 정보 조회
    @Override
    public RoomVo getRoomById(int roomId) {
        return roomMapper.getRoomById(roomId);
    }
}
