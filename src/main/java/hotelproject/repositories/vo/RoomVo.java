package hotelproject.repositories.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomVo {
	 private int id;
	    private int hotel_id;  
	    private String room_name;  
	    private int room_price;  
}
