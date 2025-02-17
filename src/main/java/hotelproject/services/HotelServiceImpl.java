package hotelproject.services;

import hotelproject.mappers.HotelMapper;
import hotelproject.repositories.HotelDao;
import hotelproject.repositories.vo.HotelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelDao hotelDao;

    // 호텔 등록
    @Override
    @Transactional
    public void insertHotel(HotelVo hotel) {
        hotelDao.insertHotel(hotel);
    }

    // 호텔 정보 수정
    @Override
    @Transactional
    public void updateHotel(HotelVo hotel) {
        hotelDao.updateHotel(hotel);
    }

    // 호텔 삭제
    @Override
    @Transactional
    public void deleteHotel(int no) {
        hotelDao.deleteHotel(no);
    }
    
    // 특정 호텔 조회
    @Override
    public HotelVo findHotelById(int no) {
        return hotelDao.findHotelById(no);
    }


    // 전체 호텔 목록 조회
    @Override
    public List<HotelVo> getAllHotels() {
        return hotelDao.findAllHotels();
    }
}
