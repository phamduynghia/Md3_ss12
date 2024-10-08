package com.ra.service;

import java.util.List;

public interface IGenericDesign <T,E>{
    void addAndUpdate(T t);
    void remove(E id);
    //    E getNewID(E id);
    int findIndexByID(E id);
    List<T> getAll();
}