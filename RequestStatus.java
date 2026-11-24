
public enum RequestStatus{
    
    DRAFT("下書き"),
    SUBMITTED("申請中"),
    APPROVED("承認済み"),
    REJECTED("差し戻し"),
    COMPLETED("完了");

    private final String label;

    RequestStatus(String label){
        this.label = label;
    }

    public String getLabel(){
        return label;
    }
}