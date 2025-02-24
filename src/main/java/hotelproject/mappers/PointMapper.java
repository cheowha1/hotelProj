package hotelproject.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import hotelproject.repositories.vo.PointHistoryVo;

@Mapper
public interface PointMapper {

	  void insertPointHistory(PointHistoryVo history);
	  int getTotalUsedPoints(@Param("userId") String userId);
	  List<PointHistoryVo> getPointHistory(@Param("userId") String userId);
}
