package hotelproject.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import hotelproject.repositories.vo.HotelVo;

@Mapper 
public interface HotelMapper {

//	List<HotelVo> getAllHotels();
    HotelVo getHotelDetails(@Param("hotelNo") int hotelNo);
    int getAvailableRooms(@Param("hotelNo") int hotelNo);
    void updateHotelRating(@Param("hotelNo") int hotelNo, @Param("rating") Double rating);
    @Select("SELECT * FROM hotel")  // XML 대신 직접 SQL 실행 가능 (XML 사용하면 삭제 가능)
    List<HotelVo> getAllHotels();
    
}
