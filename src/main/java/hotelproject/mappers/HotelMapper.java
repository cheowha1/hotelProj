package hotelproject.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import hotelproject.repositories.vo.HotelVo;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;

@Mapper 
public interface HotelMapper {

    // 4. 특정 호텔 조회
    @Select("SELECT * FROM hotel WHERE no=#{no}")
    HotelVo findHotelById(int no); 

    // 5. 전체 호텔 조회
    @Select("SELECT * FROM hotel")
    List<HotelVo> findAllHotels();    
}
