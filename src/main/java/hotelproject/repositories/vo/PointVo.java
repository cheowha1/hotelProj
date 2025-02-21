package hotelproject.repositories.vo;

public class PointVo {
	 private int id;
	    private int userNo;
	    private int pointAmount;
	    private String pointType; // 'RESERVATION' 등
	    private Date createdAt;

	    // 기본 생성자
	    public PointVo() {}

	    // 전체 필드를 포함한 생성자
	    public PointVo(int id, int userNo, int pointAmount, String pointType, Date createdAt) {
	        this.id = id;
	        this.userNo = userNo;
	        this.pointAmount = pointAmount;
	        this.pointType = pointType;
	        this.createdAt = createdAt;
	    }

	    // Getter 및 Setter
	    public int getId() {
	        return id;
	    }
	    public void setId(int id) {
	        this.id = id;
	    }

	    public int getUserNo() {
	        return userNo;
	    }
	    public void setUserNo(int userNo) {
	        this.userNo = userNo;
	    }

	    public int getPointAmount() {
	        return pointAmount;
	    }
	    public void setPointAmount(int pointAmount) {
	        this.pointAmount = pointAmount;
	    }

	    public String getPointType() {
	        return pointType;
	    }
	    public void setPointType(String pointType) {
	        this.pointType = pointType;
	    }

	    public Date getCreatedAt() {
	        return createdAt;
	    }
	    public void setCreatedAt(Date createdAt) {
	        this.createdAt = createdAt;
	    }
	}

