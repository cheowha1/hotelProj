package hotelproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hotelproject.repositories.vo.ReservationVo;
import hotelproject.services.ReservationService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // 예약 추가
    @PostMapping("/insert")
    public String insertReservation(ReservationVo reservation) {
        reservationService.insertReservation(reservation);
        return "redirect:/reservation/list";
    }

    // 예약 목록 조회
    @GetMapping("/list")
    public String listReservations(Model model) {
        model.addAttribute("reservations", reservationService.getAllReservations());
        return "reservation/list";
    }

    // 예약 상세 조회
    @GetMapping("/{reservationNo}")
    public String viewReservation(@PathVariable("reservationNo") int reservationNo, Model model) {
        model.addAttribute("reservation", reservationService.getReservationById(reservationNo));
        return "reservation/view";
    }

    // 예약 수정
    @PostMapping("/update")
    public String updateReservation(ReservationVo reservation) {
        reservationService.updateReservation(reservation);
        return "redirect:/reservation/list";
    }

    // 예약 취소
    @PostMapping("/delete/{reservationNo}")
    public String deleteReservation(@PathVariable("reservationNo") int reservationNo) {
        reservationService.deleteReservation(reservationNo);
        return "redirect:/reservation/list";
    }
    
    @GetMapping("/history")
    public ResponseEntity<List<ReservationVo>> getUserReservations(HttpSession session) {
        String email = (String) session.getAttribute("userEmail");

        if (email == null) {
            return ResponseEntity.status(401).body(null);
        }

        List<ReservationVo> reservations = reservationService.getUserReservations(email);
        return ResponseEntity.ok(reservations);
    }
    
    // 예약 시 포인트 차감
    @PostMapping("/insertWithPoints")
    public String insertReservationWithPoints(ReservationVo reservation, boolean usePoints) {
    	int result = reservationService.insertReservation(reservation, usePoints);
    	
    	if (result == -1) {
    		return "redirect:/reservation/list?error=insufficient_points";
    	}
    	
    	return "redirect:/reservation/list";
    	
   }
    
}
