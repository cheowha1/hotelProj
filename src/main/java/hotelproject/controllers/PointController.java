package hotelproject.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hotelproject.repositories.vo.PointHistoryVo;
import hotelproject.services.PointService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/users/points")
public class PointController {

	 @Autowired
	    private PointService pointService;

	    // 포인트 충전
	    @PostMapping("/charge")
	    public ResponseEntity<String> chargePoints(@RequestBody Map<String, Object> requestData, HttpSession session) {
	        try {
	            String userId = (String) session.getAttribute("userId");
	            int amount = Integer.parseInt(requestData.get("amount").toString());
	            pointService.chargePoints(amount, userId);
	            return ResponseEntity.ok("포인트 충전 완료");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 요청입니다.");
	        }
	    }

	    // 포인트 사용
	    @PostMapping("/use")
	    public ResponseEntity<?> usePoints(@RequestBody int amount, HttpSession session) {
	        String userId = (String) session.getAttribute("userId");
	        if (userId == null) {
	            return ResponseEntity.status(401).body("로그인 필요");
	        }

	        try {
	            pointService.usePoints(userId, amount);
	            return ResponseEntity.ok("포인트 사용 완료");
	        } catch (Exception e) {
	            return ResponseEntity.status(500).body("포인트 사용 실패: " + e.getMessage());
	        }
	    }

	    // 포인트 내역 조회
	    @GetMapping("/history")
	    public ResponseEntity<List<PointHistoryVo>> getPointHistory(HttpSession session) {
	        String userId = (String) session.getAttribute("userId");
	        if (userId == null) {
	            return ResponseEntity.status(401).body(null); // 로그인 필요
	        }

	        List<PointHistoryVo> history = pointService.getPointHistory(userId);
	        return ResponseEntity.ok(history);
	    }
}
