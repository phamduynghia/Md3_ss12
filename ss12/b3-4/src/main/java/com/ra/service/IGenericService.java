package com.ra.service;

import java.util.List;

public interface IGenericService <T,E>{
    List<T> findAll();
    void addAndUpdate(T t);
    void delete(E id);
    int findIndexByID(E id);

}