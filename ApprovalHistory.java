import java.time.LocalDateTime;

public class ApprovalHistory {
    private Employee actor;         // 操作した人
    private String action;          // 操作の内容（例：承認）
    private LocalDateTime dateTime; // 操作した日時

    public ApprovalHistory(Employee actor, String action){
        this.actor = actor;
        this.action = action;
        this.dateTime = LocalDateTime.now(); //今の日時
    }

    public Employee getActor() {return actor;}
    public String getAction() {return action;}
    public LocalDateTime getDateTime() {return dateTime;}
}
