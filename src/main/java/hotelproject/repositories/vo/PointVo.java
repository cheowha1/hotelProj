package hotelproject.repositories.vo;

public class PointVo {
    private int userNo;
    private int amount;
    private String type; // 'charge' or 'use'
    private String description;

    // 기본 생성자
    public PointVo() {}

    // 생성자
    public PointVo(int userNo, int amount, String type, String description) {
        this.userNo = userNo;
        this.amount = amount;
        this.type = type;
        this.description = description;
    }

    // Getter & Setter
    public int getUserNo() { return userNo; }
    public void setUserNo(int userNo) { this.userNo = userNo; }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
