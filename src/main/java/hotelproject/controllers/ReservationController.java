package hotelproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hotelproject.repositories.vo.PointHistoryVo;
import hotelproject.services.ReservationService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

	 @Autowired
	    private ReservationService reservationService;

	    @PostMapping("/book")
	    public boolean bookHotel(@RequestParam int hotelId, @RequestParam int cost, HttpSession session) {
	        String userId = (String) session.getAttribute("userId");
	        if (userId == null) {
	            throw new IllegalArgumentException("로그인이 필요합니다.");
	        }
	        return reservationService.bookHotel(userId, hotelId, cost);
	    }

	    @PostMapping("/cancel")
	    public boolean cancelReservation(@RequestParam int reservationId, HttpSession session) {
	        String userId = (String) session.getAttribute("userId");
	        if (userId == null) {
	            throw new IllegalArgumentException("로그인이 필요합니다.");
	        }
	        return reservationService.cancelReservation(userId, reservationId);
	    }
	
	    @GetMapping("/points/history")
	    public List<PointHistoryVo> getPointHistory(HttpSession session) {
	        String userId = (String) session.getAttribute("userId");
	        if (userId == null) {
	            throw new IllegalArgumentException("로그인이 필요합니다.");
	        }
	        return reservationService.getPointHistory(userId);
	    }
}
