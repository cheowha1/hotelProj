package hotelproject.repositories.vo;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PointHistoryVo {

	 private String userId;
	    private int amount;
	    private String type;
	    private LocalDateTime createdAt;

	   
}
