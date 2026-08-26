public class Department {
    int departmentId;
    String departmentName;

    public Department(String departmentName, int departmentId) {
        this.departmentName = departmentName;
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Department Name: " + departmentName
                + ", Department ID: " + departmentId;
    }

    public void showInfo() {
        System.out.println("Department Name: " + departmentName);
        System.out.println("Department ID: " + departmentId);
    }
}
