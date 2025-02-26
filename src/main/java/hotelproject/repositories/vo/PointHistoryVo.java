package hotelproject.repositories.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PointHistoryVo {

		private String userId;
	    private int amount;
	    private String type;
	    private String description;
	    private Date createdAt;

	   
}
