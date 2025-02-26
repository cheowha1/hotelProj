package hotelproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hotelproject.services.PointService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/points")
public class PointController {

	   @Autowired
	    private PointService pointService;

	    @PostMapping("/charge")
	    public boolean chargePoints(@RequestParam int amount, HttpSession session) {
	        String userId = (String) session.getAttribute("userId"); // userId 기반으로 변경
	        if (userId == null) {
	            throw new IllegalArgumentException("로그인이 필요합니다.");
	        }
	        return pointService.chargePoints(userId, amount);
	    }
	    
	    @PostMapping("/use")
	    public boolean usePoints(@RequestParam int amount, HttpSession session) {
	        String userId = (String) session.getAttribute("userId");
	        if (userId == null) {
	            throw new IllegalArgumentException("로그인이 필요합니다.");
	        }
	        return pointService.usePoints(userId, amount);
	    }
}
