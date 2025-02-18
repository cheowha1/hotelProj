package hotelproject.controllers;

import hotelproject.repositories.vo.PointVo;
import hotelproject.services.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/points")
public class PointController {

    @Autowired
    private PointService pointService;

    // 포인트 적립 (회원가입 시, 결제 시)
    @PostMapping("/earn")
    public String earnPoints(@RequestParam int userNo, @RequestParam int amount) {
        pointService.earnPoints(userNo, amount);
        return "포인트가 적립되었습니다.";
    }

    // 포인트 사용 (결제 시, 예약 시)
    @PostMapping("/use")
    public String usePoints(@RequestParam int userNo, @RequestParam int amount) {
        boolean success = pointService.usePoints(userNo, amount);
        return success ? "포인트가 사용되었습니다." : "포인트가 부족합니다.";
    }

    // 사용자의 총 포인트 조회
    @GetMapping("/total/{userNo}")
    public int getUserTotalPoints(@PathVariable int userNo) {
        return pointService.getUserTotalPoints(userNo);
    }

    // 사용자의 포인트 내역 조회
    @GetMapping("/history/{userNo}")
    public List<PointVo> getUserPointHistory(@PathVariable int userNo) {
        return pointService.getUserPointHistory(userNo);
    }
}
