public class Group {
    String groupId;
    String groupName;

    public Group(String groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public void showInfo(){
        System.out.println("Group Id: " + groupId);
        System.out.println("Group Name: " + groupName);
    }
}
