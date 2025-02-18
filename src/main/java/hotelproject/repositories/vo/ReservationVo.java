package hotelproject.repositories.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;	// 


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class ReservationVo {
	private int reservationNo; // 예약번호
	private int hotelNo;	//	호텔번호
	private int userNo;		//	회원번호
	private int roomNo;		//	방번호
	private Date checkInDate;	//	체크인 날짜
	private Date checkOutDate;	//	체크아웃 날짜
	private int guestCount;		// 	예약 시 인원수제한
	private int totalPrice;	//	총가격	
	private String PaymentStatus;		//	결제상태
	private String reservationStatus;	//	예약상태	
}
