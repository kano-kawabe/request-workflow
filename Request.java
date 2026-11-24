public class Request {
    private int id;
    private String applicantName;
    private String itemName;
    private int amount;
    private RequestStatus status;

    public Request(int id, String applicantName, String itemName, int amount){
        this.id = id;
        this.applicantName = applicantName;
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
    public void approve(){
        if(status !=RequestStatus.SUBMITTED){
            throw new IllegalStateException(
                "「" + status.getLabel() + "」の申請は承認できません");
        }
        status = RequestStatus.APPROVED;
    }

    // 指し戻す（申請中の時だけ）
    public void reject(){
        if(status !=RequestStatus.SUBMITTED){
            throw new IllegalStateException(
                "「" + status.getLabel() + "」の申請は差し戻せません");
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
    public String getApplicantName(){return applicantName;}
    public String getItemName(){return itemName;}
    public int getAmount(){return amount;}
    public RequestStatus getStatus(){return status;}
}
