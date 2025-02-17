package hotelproject.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;

@Mapper 
public interface HotelMapper {

    // 1. 호텔 등록
    @Insert("INSERT INTO hotel (name, location, district, email, phone, max_room) " +
            "VALUES (#{name}, #{location}, #{district}, #{email}, #{phone}, #{maxRoom})")
    void insertHotel(Hotel hotel);

    // 2. 호텔 정보 수정
    @Update("UPDATE hotel SET name=#{name}, location=#{location}, district=#{district}, " +
            "email=#{email}, phone=#{phone}, max_room=#{maxRoom} WHERE no=#{no}")
    void updateHotel(Hotel hotel);

    // 3. 호텔 삭제
    @Delete("DELETE FROM hotel WHERE no=#{no}")
    void deleteHotel(int no);

    // 4. 특정 호텔 조회
    @Select("SELECT * FROM hotel WHERE no=#{no}")
    Hotel findHotelById(int no);

    // 5. 전체 호텔 조회
    @Select("SELECT * FROM hotel")
    List<Hotel> findAllHotels();
}
