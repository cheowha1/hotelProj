package hotelproject.services;

import java.util.List;

import hotelproject.repositories.vo.HotelVo;

public interface HotelService {
 

	 List<HotelVo> getAllHotels();
	 HotelVo getHotelById(int hotelId);
	 boolean isHotelAvailable(int hotelNo);
	 List<String> getHotelImages(int hotelId);
	 
}

