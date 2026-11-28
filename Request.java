import java.util.ArrayList;
import java.util.List;

public class Request {
    private int id;
    private Employee applicant;
    private String itemName;
    private int amount;
    private RequestStatus status;

    private List<ApprovalHistory> histories = new ArrayList<>();

    public Request(int id, Employee applicant, String itemName, int amount){
        this.id = id;
        this.applicant = applicant;
        this.itemName = itemName;
        this.amount = amount;
        this.status = RequestStatus.DRAFT; // 作成時は必ず下書き
    }

    // 申請する
    public void submit(){
        if(status !=RequestStatus.DRAFT && status != RequestStatus.REJECTED){
            throw new IllegalStateException(
                "「" + status.getLabel() + "」の申請は提出できません");
        }
        status = RequestStatus.SUBMITTED;
        histories.add(new ApprovalHistory(applicant,"申請"));
    }

    // 承認する（申請中の時だけ）
    public void approve(Employee approver){
        if(!approver.isManager()){
            throw new IllegalStateException(
            approver.getName() + "さんは上長ではないため承認できません");
        }
        if(status != RequestStatus.SUBMITTED){
            throw new IllegalStateException(
            "「" + status.getLabel() + "」の申請は承認できません");
        }
        if(applicant.getId() == approver.getId()){
            throw new IllegalStateException(
                "自分の申請は承認できません");
        }
        status = RequestStatus.APPROVED;
        histories.add(new ApprovalHistory(approver,"承認"));
    }

    // 指し戻す（申請中の時だけ）
    public void reject(Employee approver){
        if(!approver.isManager()){
            throw new IllegalStateException(
            approver.getName() + "さんは上長でないため差し戻せません");
        }

        if(applicant.getId() == approver.getId()){
            throw new IllegalStateException(
                "自分の申請は差し戻せません");
        }

        if(status != RequestStatus.SUBMITTED){
            throw new IllegalStateException(
            "「" + status.getLabel() + "」の申請は差し戻しできません");
        }

        status = RequestStatus.REJECTED;
        histories.add(new ApprovalHistory(approver,"差し戻し"));
    }

    // 完了にする（承認済みのときだけ）
    public void complete(Employee approver){
        if(status !=RequestStatus.APPROVED){
            throw new IllegalStateException(
                "「" + status.getLabel() + "」の申請は完了にできません");
        }
        status = RequestStatus.COMPLETED;
        histories.add(new ApprovalHistory(approver,"完了"));
    }

    public List<ApprovalHistory> getHistroes() { return histories; }

    public int getID() {return id;}
    public Employee getApplicant(){return applicant;}
    public String getItemName(){return itemName;}
    public int getAmount(){return amount;}
    public RequestStatus getStatus(){return status;}
    public List<ApprovalHistory> getHistories() {return histories;}
}