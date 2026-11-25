public class Employee {
    private int id;
    private String name;
    private Role role;

    public Employee(int id, String name, Role role){
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public boolean isManager(){
        return role == Role.MANAGER;
    }

    public int getID() { return id;}
    public String getName() { return name;}
    public Role getRole() { return role;}
    
}
