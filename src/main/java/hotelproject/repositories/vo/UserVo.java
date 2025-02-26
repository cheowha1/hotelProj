package hotelproject.repositories.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVo {
    
	 private String id;
	    private String password;
	    private String name;
	    private String nickname;
	    private String phone;
	    private String ssn; // 주민번호 (마스킹 처리 필요)
	    private String grade; // 회원 등급
	    private int point; // 포인트
	    private String reference; // 추천인 닉네임
	    private int no;
	    private String role;
	    

}