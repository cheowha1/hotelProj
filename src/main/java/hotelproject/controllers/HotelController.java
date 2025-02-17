package hotelproject.controllers;

import hotelproject.repositories.vo.HotelVo;
import hotelproject.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    // 전체 호텔 목록 조회 (GET /hotels)
    @GetMapping
    public List<HotelVo> getAllHotels() {
        return hotelService.getAllHotels();
    }

    // 특정 호텔 조회 (GET /hotels/{no})
    @GetMapping("/{no}")
    public HotelVo getHotelById(@PathVariable int no) {
        return hotelService.findHotelById(no);
    }

    // 호텔 등록 (POST /hotels)
    @PostMapping
    public String insertHotel(@RequestBody HotelVo hotel) {
        hotelService.insertHotel(hotel);
        return "호텔이 성공적으로 등록되었습니다!";
    }

    // 호텔 정보 수정 (PUT /hotels/{no})
    @PutMapping("/{no}")
    public String updateHotel(@PathVariable int no, @RequestBody HotelVo hotel) {
        hotel.setNo(no);  // URL에서 받은 no 값을 객체에 설정
        hotelService.updateHotel(hotel);
        return "호텔 정보가 수정되었습니다!";
    }

    // 호텔 삭제 (DELETE /hotels/{no})
    @DeleteMapping("/{no}")
    public String deleteHotel(@PathVariable int no) {
        hotelService.deleteHotel(no);
        return "호텔이 삭제되었습니다!";
    }
}
