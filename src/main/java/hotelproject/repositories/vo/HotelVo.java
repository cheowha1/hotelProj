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
	private int no;
	private String name;
	private String location;
	private String district;
	private String email;
	private String phone;
	private int maxRoom;

}
