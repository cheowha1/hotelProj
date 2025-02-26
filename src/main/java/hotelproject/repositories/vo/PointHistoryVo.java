package hotelproject.repositories.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor // 생성자 자동으로 만들어주는
@NoArgsConstructor
public class PointHistoryVo {

	 private String userId;
	    private int amount;
	    private String type;
	    private LocalDateTime createdAt;

	   
}
