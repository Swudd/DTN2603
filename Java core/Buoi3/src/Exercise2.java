import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class Exercise2 {
    public static ArrayList<Account> question1(int count){
        ArrayList<Account> accounts = new ArrayList<>();
        for(int i=0;i<count;i++){
            String email = "email "+i;
            String username = "username "+i;
            String fullname = "fullname "+i;
            Department d = new Department("dep",1);
            Position p = new Position(1, PositionName.DEV);
            Account a = new Account(i, email, username, fullname, d, p, new Date(), 24);
            accounts.add(a);
        }
        return accounts;
    }
}
