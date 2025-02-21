package hotelproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hotelproject.repositories.vo.ReservationVo;
import hotelproject.services.ReservationService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

	 @Autowired
	    private ReservationService reservationService;

	    // 유저가 포인트를 사용하여 호텔 예약
	    @PostMapping("/book")
	    public String reserveHotelWithPoints(@RequestParam int hotelNo, @RequestParam int amount, HttpSession session) {
	        Integer userNo = (Integer) session.getAttribute("userNo");
	        if (userNo == null) {
	            return "세션이 만료되었습니다. 다시 로그인해주세요.";
	        }
	        boolean success = reservationService.reserveHotelWithPoints(userNo, hotelNo, amount);
	        return success ? "호텔 예약 성공" : "포인트가 부족하여 예약 실패";
	    }

	    // 유저의 호텔 예약 목록 조회
	    @GetMapping("/list")
	    public List<ReservationVo> getUserReservations(HttpSession session) {
	        Integer userNo = (Integer) session.getAttribute("userNo");
	        if (userNo == null) {
	            return null;
	        }
	        return reservationService.getUserReservations(userNo);
	    }

	    // 유저의 호텔 예약 내역 조회 (히스토리)
	    @GetMapping("/history")
	    public List<ReservationVo> getReservationHistory(HttpSession session) {
	        Integer userNo = (Integer) session.getAttribute("userNo");
	        if (userNo == null) {
	            return null;
	        }
	        return reservationService.getReservationHistory(userNo);
	    }

	    // 유저가 호텔 예약 취소 (포인트 환불 포함)
	    @PostMapping("/cancel")
	    public String cancelReservation(@RequestParam int reservationNo, @RequestParam int amount, HttpSession session) {
	        Integer userNo = (Integer) session.getAttribute("userNo");
	        if (userNo == null) {
	            return "세션이 만료되었습니다. 다시 로그인해주세요.";
	        }
	        boolean success = reservationService.cancelReservation(reservationNo, userNo, amount);
	        return success ? "호텔 예약 취소 완료 (포인트 환불)" : "예약 취소 실패";
	    }
}
