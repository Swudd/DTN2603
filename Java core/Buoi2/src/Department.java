public class Department {
    int departmentId;
    String departmentName;

    public Department(String departmentName, int departmentId) {
        this.departmentName = departmentName;
        this.departmentId = departmentId;
    }



    public void showInfo() {
        System.out.println("Department Name: " + departmentName);
        System.out.println("Department ID: " + departmentId);
    }
}
