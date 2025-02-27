package hotelproject.repositories.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ReservationVo {
    private int id;  // 예약 ID (PK)
    private String userId;  // 유저 ID (VARCHAR)
    private int hotelId;  // 호텔 ID (DB에서 INT)
    private int roomId;  // 룸 ID (DB에서 INT)
    private String roomName;  // 룸 이름
    private String checkInDate;  // 체크인 날짜 (DB에서 DATE, String으로 저장)
    private String checkOutDate;  // 체크아웃 날짜 (DB에서 DATE, String으로 저장)
    private int adultCount;  // 성인 수 (DB에서 INT)
    private int childCount;  // 아이 수 (DB에서 INT)
    private int totalPrice;  // 총 금액 (DB에서 INT)
    private String reservationStatus;  // 예약 상태 (DB에서 VARCHAR)

    public ReservationVo(String userId, int hotelId, int roomId, String roomName, String checkInDate, String checkOutDate, int adultCount, int childCount, int totalPrice, String reservationStatus) {
        this.userId = userId;
        this.hotelId = hotelId;
        this.roomId = roomId;
        this.roomName = roomName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.adultCount = adultCount;
        this.childCount = childCount;
        this.totalPrice = totalPrice;
        this.reservationStatus = reservationStatus;
    }

}
