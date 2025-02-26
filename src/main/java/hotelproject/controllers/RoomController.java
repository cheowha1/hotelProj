package hotelproject.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hotelproject.repositories.vo.RoomVo;
import hotelproject.services.RoomService;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // 특정 호텔의 객실 리스트 조회
    @GetMapping("/hotel/{hotelId}")
    public List<RoomVo> getRoomsByHotelId(@PathVariable int hotelId) {
        return roomService.getRoomsByHotelId(hotelId);
    }

    @GetMapping("/{roomId}/images")
    public List<String> getRoomImages(@PathVariable int roomId) {
        return roomService.getRoomImages(roomId);
    }
    // 특정 객실 정보 조회
    @GetMapping("/{roomId}")
    public RoomVo getRoomById(@PathVariable int roomId) {
        return roomService.getRoomById(roomId);
    }
}
