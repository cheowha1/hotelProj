package hotelproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotelproject.mappers.HotelMapper;
import hotelproject.repositories.vo.HotelVo;


@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelMapper hotelmapper;

    // 특정 호텔 조회
    @Override
    public HotelVo findHotelById(int no) {
        return hotelmapper.findHotelById(no);
    }


    // 전체 호텔 목록 조회
    @Override
    public List<HotelVo> getAllHotels() {
        return hotelmapper.findAllHotels();
    }
}
