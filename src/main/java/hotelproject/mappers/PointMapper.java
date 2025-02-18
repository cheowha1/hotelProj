package hotelproject.mappers;

import hotelproject.repositories.vo.PointVo;
import org.apache.ibatis.annotations.Mapper;
import java.time.LocalDateTime;
import java.util.*;

@Mapper
public class PointMapper {

    // 메모리 기반 저장소 (userNo를 키로, 포인트 내역 리스트를 값으로 저장)
    private final Map<Integer, List<PointVo>> pointData = new HashMap<>();

    // 1. 포인트 적립
    public void insertPoint(PointVo point) {
    	if (point.getCreatedAt() == null) {
    		point.setCreatedAt(null);	//	createdAt 값이 없으면 현재 시간설정 		
    	}
    	pointData.computeIfAbsent(point.getUserNo(), k -> new ArrayList<>()).add(point);
    }

    // 2. 회원의 총 포인트 조회 (모든 포인트 내역 합산)
    public Integer getUserTotalPoints(int userNo) {
        return pointData.getOrDefault(userNo, new ArrayList<>())
                        .stream()
                        .mapToInt(PointVo::getPointAmount)
                        .sum();
    }

    // 3. 회원의 포인트 내역 조회 (최근순 정렬)
    public List<PointVo> getUserPointHistory(int userNo) {
        List<PointVo> history = pointData.getOrDefault(userNo, new ArrayList<>());
        history.sort((p1, p2) -> p2.getCreatedAt().compareTo(p1.getCreatedAt()));  // 최신순 정렬
        return history;
    }

    // 4. 포인트 사용 (포인트 부족하면 false 반환)
    public boolean usePoints(int userNo, int amount) {
        int totalPoints = getUserTotalPoints(userNo);
        if (totalPoints < amount) {
            return false;  // 포인트 부족
        }

        PointVo point = new PointVo(userNo, -amount, "USE");	//	수정된 생성자 사용 
        pointData.get(userNo).add(point);
        return true;
    }
}
