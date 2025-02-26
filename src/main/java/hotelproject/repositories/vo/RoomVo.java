package hotelproject.repositories.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomVo {
    private int id;
    private int hotelId;
    private String roomName;
    private int roomPrice;
}
