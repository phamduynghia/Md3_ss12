package com.ra.dao;

import java.util.List;

public interface IUserDao<T,E>{
    List<T> getAll();
    void addAndUpdate(T t);
    void delete(E id);
    E getNewId(T id);
    int findIndexById(E id);

}