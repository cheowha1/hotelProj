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


}
