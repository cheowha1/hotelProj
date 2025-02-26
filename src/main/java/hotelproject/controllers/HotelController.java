package hotelproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hotelproject.repositories.vo.HotelVo;
import hotelproject.services.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	
	 @Autowired
	    private HotelService hotelService;

	    @GetMapping
	    public List<HotelVo> getAllHotels() {
	        return hotelService.getAllHotels();
	    }

	 
	    @GetMapping("/{hotelId}")
	    public HotelVo getHotelById(@PathVariable int hotelId) {
	        return hotelService.getHotelById(hotelId);
	    }
	    
	    @GetMapping("/{hotelId}/images")
	    public List<String> getHotelImages(@PathVariable int hotelId) {
	        return hotelService.getHotelImages(hotelId);
	    }

	    @GetMapping("/availability/{hotelNo}")
	    public boolean checkHotelAvailability(@PathVariable int hotelNo) {
	        return hotelService.isHotelAvailable(hotelNo);
	    }
}
