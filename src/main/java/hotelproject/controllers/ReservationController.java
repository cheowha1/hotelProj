package hotelproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import hotelproject.repositories.vo.ReservationVo;
import hotelproject.services.ReservationService;

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
}
