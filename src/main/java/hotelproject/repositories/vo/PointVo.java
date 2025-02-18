package hotelproject.repositories.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PointVo {
    private int userNo;           // 회원 번호
    private int pointAmount;      // 포인트 양 (적립/사용)
    private String pointType;     // 포인트 유형 (EARN, USE)
    private LocalDateTime createdAt; // 포인트 적립/사용 시간 추가

    // createdAt이 자동으로 설정되는 생성자 추가
    public PointVo(int userNo, int pointAmount, String pointType) {
        this.userNo = userNo;
        this.pointAmount = pointAmount;
        this.pointType = pointType;
        this.createdAt = LocalDateTime.now(); // 생성 시 자동으로 현재 시간 설정
    }
}
