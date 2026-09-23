package backend.service.impl;

import backend.repository.IQLAccountRepository;
import backend.repository.IQLDepartmentRepository;
import backend.repository.IQLPositionRepository;
import backend.repository.impl.QLAccountRepositoryImpl;
import backend.repository.impl.QLDepartmentRepositoryImpl;
import backend.repository.impl.QLPositionReposirotyImpl;
import backend.service.IQLAccountService;
import entity.Account;
import entity.Department;
import entity.Position;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static frontend.Function.isValidEmail;

public class QLAccountServiceImpl implements IQLAccountService {
    private IQLAccountRepository repository;
    private IQLDepartmentRepository departmentRepository =  new QLDepartmentRepositoryImpl();
    private IQLPositionRepository positionRepository = new QLPositionReposirotyImpl();

    public QLAccountServiceImpl(){repository = new QLAccountRepositoryImpl();}



    @Override
    public List<Account> GetAllAccounts() {
        List<Account> accounts = repository.GetAllAccounts();
        return accounts;
    }

    @Override
    public List<Account> FindAccountByName(String name) {
        List<Account> accounts = repository.FindAccountByName(name);
        return accounts;
    }

    @Override
    public boolean AddAccount(Account account) {
        return repository.AddAccount(account);
    }

    @Override
    public boolean DeleteAccount(int accountId) {
        return repository.DeleteAccount(accountId);
    }

    @Override
    public boolean UpdateAccount(String accountName, int accountId) {
        return repository.UpdateAccount(accountName,accountId);
    }

    @Override
    public boolean CheckUsernameExist(String username) {
        return repository.CheckUsernameExist(username);
    }

    @Override
    public boolean CheckEmailExist(String email) {
        return  repository.CheckEmailExist(email);
    }

    @Override
    public boolean CheckAccountIdExist(int accountId) {
        return repository.CheckAccountIdExist(accountId);
    }

    @Override
    public String importCSV(String url) {
        if (!url.endsWith(".csv")) {
            return "File không đúng định dạng!";
        }

        File file = new File(url);
        if (!file.exists()) {
            return "File không tồn tại!";
        }

        List<Account> accounts = new ArrayList<>();
        List<String> listErrors = new ArrayList<>();
        String header = "";

        try(BufferedReader br = new BufferedReader(new FileReader(url))){
            String line;
            header = br.readLine();
            while ((line = br.readLine()) != null) {
                String message =this.validateAccount(line, accounts);
                if (Objects.nonNull(message)) {
                    listErrors.add(message);
                }
            }
        } catch ( Exception e ){
            e.printStackTrace();
        }

        for (Account account : accounts) {
            repository.AddAccount(account);
        }

        if (!listErrors.isEmpty()) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Trung\\OneDrive\\Desktop\\DTN2603\\Java core\\csv\\input_errors.csv"));
                bw.write(header + ",error_messages");
                bw.newLine();
                for (String error : listErrors) {
                    bw.write(error);
                    bw.newLine();
                }
                bw.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listErrors.isEmpty() ? "Import thành công" : "Đã xuất ra file lỗi input_errors.csv";
    }

    public String validateAccount(String line, List<Account> accounts) {
        Account account = new Account();
        List<String> errors = new ArrayList<>();

        String[] values = line.split(",");
        String email = values[0];
        boolean checkEmailExist = repository.CheckEmailExist(email);
        if (checkEmailExist){
            errors.add("Email da ton tai");
        } else if(isValidEmail(email)){
            errors.add("Email khong hop le");
        }

        String username = values[1];

        if (username.length() <= 6 || username.length() >= 100) {
            errors.add("Username phải 6 đến 100 kí tự");
        } else if (repository.CheckUsernameExist(username)){
            errors.add("Username da ton tai");
        }

        String fullName = values[2];
        if (fullName.length() <= 6 || fullName.length() >= 100) {
            errors.add("Username phải 6 đến 100 k tự");
        }

        String departmentId = values[3];
        if (!departmentRepository.CheckExists(Integer.parseInt(departmentId))) {
            errors.add("Phòng ban không tồn tại.");
        }

        String positionId = values[4];
        if (!positionRepository.CheckExists(Integer.parseInt(positionId))) {
            errors.add("Phòng ban không tồn tại.");
        }

        String gender = values[6];
        if (!gender.equalsIgnoreCase("F")
                || !gender.equalsIgnoreCase("U")
                || !gender.equalsIgnoreCase("M")) {
            errors.add("Gender không hợp lệ(F,M,U)");
        }
        account.setUsername(username);
        account.setEmail(email);
        account.setFullname(fullName);
        account.setDepartment(new Department(Integer.parseInt(departmentId), ""));
        account.setPosition(new Position(Integer.parseInt(positionId), null));
        if (errors.isEmpty()) {
            accounts.add(account);
            return null;
        } else {
            String error = String.join(", ", errors);
            line = line + "," + error;
        }
        return line;
    }
}
