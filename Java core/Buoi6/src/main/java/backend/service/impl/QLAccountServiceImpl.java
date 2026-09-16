package backend.service.impl;

import backend.repository.IQLAccountRepository;
import backend.repository.impl.QLAccountRepositoryImpl;
import backend.service.IQLAccountService;
import entity.Account;

import java.util.List;

public class QLAccountServiceImpl implements IQLAccountService {
    private IQLAccountRepository repository;

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
}
