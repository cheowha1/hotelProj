package hotelproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hotelproject.repositories.vo.PointVo;
import hotelproject.services.PointService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/points")
public class PointController {

	@RestController
	@RequestMapping("/points")
	public class PointController {

	    @Autowired
	    private PointService pointService;

	    // 포인트 사용하여 호텔 예약 (세션 기반 사용자)
	    @PostMapping("/useForReservation")
	    public String usePointsForReservation(@RequestParam int amount, HttpSession session) {
	        Integer userNo = (Integer) session.getAttribute("userNo");
	        if (userNo == null) {
	            return "세션이 만료되었습니다. 다시 로그인해주세요.";
	        }
	        boolean success = pointService.usePointsForReservation(userNo, amount);
	        return success ? "포인트 사용 성공 (예약 완료)" : "포인트 부족으로 예약 실패";
	    }

	    // 포인트 사용 내역 조회 (히스토리)
	    @GetMapping("/history")
	    public List<PointVo> getPointHistory(HttpSession session) {
	        Integer userNo = (Integer) session.getAttribute("userNo");
	        if (userNo == null) {
	            return null;
	        }
	        return pointService.getPointHistory(userNo);
	    }

	    // 마이페이지에서 현재 포인트 조회
	    @GetMapping("/current")
	    public int getCurrentPoints(HttpSession session) {
	        Integer userNo = (Integer) session.getAttribute("userNo");
	        if (userNo == null) {
	            return 0;
	        }
	        return pointService.getUserTotalPoints(userNo);
	    }
	    
	}
}
