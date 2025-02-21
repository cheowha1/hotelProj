package hotelproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hotelproject.repositories.vo.HotelVo;
import hotelproject.repositories.vo.ReviewVo;
import hotelproject.services.HotelService;
import hotelproject.services.ReviewService;

@RestController
@RequestMapping("/hotels")
public class HotelController {

	@Autowired
    private HotelService hotelService;

    @Autowired
    private ReviewService reviewService;

    // 호텔 목록 조회
    @GetMapping("")
    public List<HotelVo> getAllHotels() {
        return hotelService.getAllHotels();
    }

    // 특정 호텔 상세 조회 (호텔 정보 + 리뷰 포함)
    @GetMapping("/details/{hotelNo}")
    public HotelDetailsResponse getHotelDetails(@PathVariable int hotelNo) {
        HotelVo hotel = hotelService.getHotelById(hotelNo);
        List<ReviewVo> reviews = reviewService.getReviewsByHotel(hotelNo);
        return new HotelDetailsResponse(hotel, reviews);
    }

    // 내부 DTO 클래스 (호텔 + 리뷰 데이터 반환용)
    public static class HotelDetailsResponse {
        private HotelVo hotel;
        private List<ReviewVo> reviews;

        public HotelDetailsResponse(HotelVo hotel, List<ReviewVo> reviews) {
            this.hotel = hotel;
            this.reviews = reviews;
        }

        public HotelVo getHotel() { return hotel; }
        public List<ReviewVo> getReviews() { return reviews; }
    }
}
