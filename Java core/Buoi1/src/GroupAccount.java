import java.util.Date;

public class GroupAccount {
    int  groupId;
    Account account;
    Date joinDate;

    public GroupAccount(int groupId, Account account, Date joinDate) {
        this.groupId = groupId;
        this.account = account;
        this.joinDate = joinDate;
    }

    public void showInfo(){
        System.out.println("Group Id: " + groupId);
        System.out.println("Account Id: " + account.accountId);
        System.out.println("Create Date: " + joinDate);
    }
}
