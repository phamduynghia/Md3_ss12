package com.ra.service.unit;

import com.ra.dao.IGenericDao;
import com.ra.dao.unit.AccountDaoImpl;
import com.ra.model.Account;
import com.ra.service.IGenericService;

import java.util.List;

public class AccountImpl implements IGenericService<Account,Integer> {
    private static final IGenericDao<Account, Integer> accountDao = new AccountDaoImpl();
    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public void addAndUpdate(Account account) {
        Integer id=account.getAccId();
        int index=findIndexByID(id);
        if(index!=-1){
            Account existingAccount=findAll().get(index);
            existingAccount.setUserName(account.getUserName());
            existingAccount.setPassword(account.getPassword());
            existingAccount.setPermission(account.isPermission());
            existingAccount.setAccStatus(account.isAccStatus());
            accountDao.andAndUpdate(existingAccount);
        }
        accountDao.andAndUpdate(account);

    }

    @Override
    public void delete(Integer id) {
        accountDao.remove(id);
    }

    @Override
    public int findIndexByID(Integer id) {
        return accountDao.findIndexByID(id);
    }
}