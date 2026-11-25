public enum Role {
    STAFF("一般"),
    MANAGER("上長");

    private final String label;

    Role(String label){
        this.label = label;
    }

    public String getLabel(){
        return label;
    }
}
