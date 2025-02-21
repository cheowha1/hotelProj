package hotelproject.services;

import java.util.List;
import hotelproject.repositories.vo.HotelVo;

public interface HotelService {
    HotelVo findHotelById(int no);	// 특정 호텔조회
    List<HotelVo> getAllHotels();	//	전체 호텔 목록 조회   
}
