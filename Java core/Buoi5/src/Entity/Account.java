package Entity;

import java.time.LocalDate;

public class Account {
    private int acountId;
    private String email;
    private String username;
    private String fullname;
    private int departmentId;
    private int positionId;
    private LocalDate createDate;
    private Gender gender;

    public Account() {
    }

    public Account(int acountId, String email, String username, String fullname, int departmentId, int positionId, LocalDate createDate, Gender gender) {
        this.acountId = acountId;
        this.email = email;
        this.username = username;
        this.fullname = fullname;
        this.departmentId = departmentId;
        this.positionId = positionId;
        this.createDate = createDate;
        this.gender = gender;
    }

    public int getAcountId() {
        return acountId;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getFullname() {
        return fullname;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public int getPositionId() {
        return positionId;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public Gender getGender() {
        return gender;
    }
}
