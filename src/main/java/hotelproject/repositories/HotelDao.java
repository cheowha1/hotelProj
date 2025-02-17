package hotelproject.repositories;

import java.util.List;
import hotelproject.repositories.vo.HotelVo;

public interface HotelDao {
    void insertHotel(HotelVo hotel);	//	호텔등록
    void updateHotel(HotelVo hotel);	//	호텔수정
    void deleteHotel(int no);			//	호텔삭제
    HotelVo findHotelById(int no);		//	특정 호텔조회
    List<HotelVo> findAllHotels();		//	전체 호텔목록 조회
}
