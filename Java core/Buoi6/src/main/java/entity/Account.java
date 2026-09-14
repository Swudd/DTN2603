package entity;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Account {
    private int acountId;
    private String email;
    private String username;
    private String fullname;
    private Department department;
    private Position position;
    private LocalDate createDate;
    private Gender gender;

    @Override
    public String toString() {
        return "Account{" +
                "acountId=" + acountId +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", fullname='" + fullname + '\'' +
                ", department=" + department.getDepartmentName() +
                ", position=" + position.getPositionName() +
                ", createDate=" + createDate +
                ", gender=" + gender +
                '}';
    }
}
