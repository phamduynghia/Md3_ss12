package com.ra.service.unit;

import com.ra.dao.IUserDao;
import com.ra.dao.unit.IUserDaoImpl;
import com.ra.model.User;
import com.ra.service.IGenericDesign;

import java.util.List;

public class UserImpl implements IGenericDesign<User, Integer> {
    private static final IUserDao<User,Integer> userDao=new IUserDaoImpl();
    @Override
    public void addAndUpdate(User user) {
        Integer id = user.getId();
//        if (id == null) {
//            User newUser = new User(null,user.getName(),user.getEmail(),user.getCountry());
//            userDao.addAndUpdate(newUser);
//
//        }
//        else {
        int index= findIndexByID(id);
        if (index !=-1){
            User existingUser=getAll().get(index);
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setCountry(user.getCountry());
            userDao.addAndUpdate(existingUser);
        } else {
            User newUser = new User(id,user.getName(),user.getEmail(),user.getCountry());
            userDao.addAndUpdate(newUser);

//            }
        }
    }

    @Override
    public void remove(Integer id) {
        userDao.delete(id);

    }


    public Integer getNewID(Integer id) {
        return 0;
    }

    @Override
    public int findIndexByID(Integer id) {
        return userDao.findIndexById(id);
    }

    @Override
    public List<User> getAll() {

        return userDao.getAll();
    }
}