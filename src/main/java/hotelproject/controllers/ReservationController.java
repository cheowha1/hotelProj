package hotelproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hotelproject.services.ReservationService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

	
	@Autowired
	private ReservationService reservationService;
	
	  public ReservationController(ReservationService reservationService) {
	        this.reservationService = reservationService;
	    }

	    @PostMapping("/book")
	    public ResponseEntity<String> bookReservation(@RequestBody ReservationRequest request, HttpSession session) {
	        String userId = (String) session.getAttribute("userId");

	        if (userId == null) {
	            return ResponseEntity.status(401).body("로그인이 필요합니다.");
	        }

	        boolean success = reservationService.bookReservation(request, userId);
	        return success ? ResponseEntity.ok("예약 성공!") : ResponseEntity.status(500).body("예약 실패!");
	    }
	    
	    @PostMapping("/cancel")
	    public String cancelReservation(@RequestBody ReservationCancelRequest request) {
	        reservationService.cancelReservation(request.reservationId());
	        return "예약이 취소되었습니다.";
	    }

	    /** ✅ Request DTO (record 활용) */
	    public record ReservationCancelRequest(int reservationId) {}
}
