package hotelproject.repositories.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVo {
    
	    private int no;  // 사용자 고유 번호
	    private String id;
	    private String password;
	    private String name;
	    private String nickname;
	    private String ssn;
	    private String phone;
	    private String referenceId;
	    private String grade;// 회원 등급
	    private int point;
	    private String role;
	    private int totalUsedPoint;
	    

}