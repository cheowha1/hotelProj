package hotelproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotelproject.mappers.HotelMapper;
import hotelproject.repositories.vo.HotelVo;


@Service
public class HotelServiceImpl implements HotelService {

	  @Autowired
	    private HotelMapper hotelMapper;

	    @Override
	    public List<HotelVo> getAllHotels() {
	        return hotelMapper.getAllHotels();
	    }

	    @Override
	    public HotelVo getHotelDetails(int hotelNo) {
	        return hotelMapper.getHotelDetails(hotelNo);
	    }

	    @Override
	    public boolean isHotelAvailable(int hotelNo) {
	        return hotelMapper.getAvailableRooms(hotelNo) > 0;
	    }
  
}
