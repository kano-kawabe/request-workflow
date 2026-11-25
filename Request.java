public class Request {
    private int id;
    private Employee applicant;
    private String itemName;
    private int amount;
    private RequestStatus status;

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
        status = RequestStatus.APPROVED;
    }

    // 指し戻す（申請中の時だけ）
    public void reject(Employee approver){
        if(!approver.isManager()){
            throw new IllegalStateException(
            approver.getName() + "さんは上長でないため差し戻せません");
        }
        if(status != RequestStatus.SUBMITTED){
            throw new IllegalStateException(
            "「" + status.getLabel() + "」の申請は差し戻しできません");
        }
        status = RequestStatus.REJECTED;
    }

    // 完了にする（承認済みのときだけ）
    public void complete(){
        if(status !=RequestStatus.APPROVED){
            throw new IllegalStateException(
                "「" + status.getLabel() + "」の申請は完了にできません");
        }
        status = RequestStatus.COMPLETED;
    }

    public int getID() {return id;}
    public Employee getApplicant(){return applicant;}
    public String getItemName(){return itemName;}
    public int getAmount(){return amount;}
    public RequestStatus getStatus(){return status;}
}
