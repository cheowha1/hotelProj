package hotelproject.repositories.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class HotelVo {
	private int no; //	호텔번호
	private String name;	//	호텔이름
	private String location;	//	호텔지역
	private String district;		
	private String email;	//	호텔 이메일
	private String phone;	//	
	private int maxRoom;	//	수용인원

}