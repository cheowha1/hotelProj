package hotelproject.repositories;

import hotelproject.repositories.HotelDao;
import hotelproject.repositories.vo.HotelVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class HotelDaoImpl implements HotelDao {

    @Autowired
    private SqlSession sqlSession;

    private static final String NAMESPACE = "hotelproject.mappers.HotelMapper.";

    // 호텔 등록
    @Override
    public void insertHotel(HotelVo hotel) {
        sqlSession.insert(NAMESPACE + "insertHotel", hotel);
    }

    // 호텔 정보 수정
    @Override
    public void updateHotel(HotelVo hotel) {
        sqlSession.update(NAMESPACE + "updateHotel", hotel);
    }

    // 호텔 삭제
    @Override
    public void deleteHotel(int no) {
        sqlSession.delete(NAMESPACE + "deleteHotel", no);
    }

    // 특정 호텔 조회
    @Override
    public HotelVo findHotelById(int no) {
        return sqlSession.selectOne(NAMESPACE + "findHotelById", no);
    }

    // 전체 호텔 목록 조회
    @Override
    public List<HotelVo> findAllHotels() {
        return sqlSession.selectList(NAMESPACE + "findAllHotels");
    }
}
