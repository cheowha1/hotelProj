package hotelproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hotelproject.repositories.vo.ReservationVo;
import hotelproject.services.ReservationService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

	
	@Autowired
	private ReservationService reservationService;
	
	 @PostMapping("/book")
	    public ResponseEntity<String> bookHotel(@RequestBody ReservationVo request, HttpSession session) {
	        if (session.getAttribute("userId") == null) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
	        }

	        boolean success = reservationService.bookHotel(session, request.getHotelId(), request.getCost());
	        return success ? ResponseEntity.ok("호텔 예약이 완료되었습니다.") :
	                         ResponseEntity.status(HttpStatus.BAD_REQUEST).body("예약 실패");
	    }

	    @PostMapping("/cancel")
	    public ResponseEntity<String> cancelReservation(@RequestBody ReservationVo request, HttpSession session) {
	        if (session.getAttribute("userId") == null) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
	        }

	        boolean success = reservationService.cancelReservation(session, request.getReservationId());
	        return success ? ResponseEntity.ok("예약이 취소되었습니다.") :
	                         ResponseEntity.status(HttpStatus.BAD_REQUEST).body("예약 취소 실패");
	    }

	    @GetMapping("/user")
	    public ResponseEntity<List<ReservationVo>> getUserReservations(HttpSession session) {
	        if (session.getAttribute("userId") == null) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	        }

	        List<ReservationVo> reservations = reservationService.getUserReservations(session);
	        return ResponseEntity.ok(reservations);
	    }
}
