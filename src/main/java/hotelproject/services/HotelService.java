package hotelproject.services;

import java.util.List;

import hotelproject.repositories.vo.HotelVo;

public interface HotelService {
 

	 List<HotelVo> getAllHotels();
	 HotelVo getHotelDetails(int hotelNo);
	 boolean isHotelAvailable(int hotelNo);
}

