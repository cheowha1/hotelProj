package hotelproject.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import hotelproject.repositories.vo.HotelVo;

@Mapper 
public interface HotelMapper {

	List<HotelVo> getAllHotels();
    HotelVo getHotelDetails(@Param("hotelNo") int hotelNo);
    int getAvailableRooms(@Param("hotelNo") int hotelNo);
    void updateHotelRating(@Param("hotelNo") int hotelNo, @Param("rating") Double rating);
}
