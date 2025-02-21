package hotelproject.repositories.vo;

public class UserVo {
    private Long id;         // DB의 PK (필요시)
    private String email;
    private String nickname;
    private String password;
    private String ssn;
    private String phone;
    private String grade;      // 기본 등급 (예: "일반")
    private String name;
    private int point;

    
    // 기본 생성자
    public UserVo(String email, String password, String nickname, int point) {
    	this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.point = point;
    }
    
    // Getter & Setter
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getSsn() {
        return ssn;
    }
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPoint() { return point; }
    public void setPoint(int point) { this.point = point; }

}