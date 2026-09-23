package backend.controller;

import backend.service.IQLAccountService;
import backend.service.impl.QLAccountServiceImpl;
import entity.Account;

import java.util.List;

public class QLAccountController {
    private IQLAccountService service;
    public QLAccountController(){service = new QLAccountServiceImpl(); }

    public List<Account> getAllAccount(){
        List<Account> accounts = service.GetAllAccounts();
        return accounts;
    }

    public List<Account> getAccountByName(String accountName){
        List<Account> accounts = service.FindAccountByName(accountName);
        return accounts;
    }

    public boolean AddAccount(Account account){
        return service.AddAccount(account);
    }

    public boolean DeleteAccount(int accountId){
        return service.DeleteAccount(accountId);
    }

    public boolean UpdateAccount(String accountName, int accountId){
        return service.UpdateAccount(accountName,accountId);
    }

    public boolean CheckUsernameExist(String username){
        return service.CheckUsernameExist(username);
    }

    public boolean CheckEmailExist(String email){
        return service.CheckEmailExist(email);
    }

    public boolean CheckAccountIdExist(int accountId){
        return service.CheckAccountIdExist(accountId);
    }

    public String importCSV(String url) {
        return service.importCSV(url);

    }
}
