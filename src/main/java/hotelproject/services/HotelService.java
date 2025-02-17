package hotelproject.services;

import java.util.List;
import hotelproject.repositories.vo.HotelVo;

public interface HotelService {
    void insertHotel(HotelVo hotel);	//	호텔등록
    void updateHotel(HotelVo hotel);	//	호텔 정보수정
    void deleteHotel(int no);	//	호텔삭제
    HotelVo findHotelById(int no);	// 특정 호텔조회
    List<HotelVo> getAllHotels();	//	전체 호텔 목록 조회
    
}
