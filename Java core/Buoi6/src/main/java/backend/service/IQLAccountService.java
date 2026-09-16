package backend.service;

import entity.Account;

import java.util.List;

public interface IQLAccountService {
    List<Account> GetAllAccounts();
    List<Account> FindAccountByName(String name);
    boolean AddAccount(Account account);
    boolean DeleteAccount(int accountId);
    boolean UpdateAccount(String accountName,  int accountId);
    boolean CheckUsernameExist(String username);
    boolean CheckEmailExist(String email);
    boolean CheckAccountIdExist(int accountId);
}
