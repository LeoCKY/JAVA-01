package com.l10.q6.dao;

public interface BaseDAO<T> {

    T queryById(Integer id);

    int insert(T t);

    int update(T t);

    int deleteById(Integer id);
}
