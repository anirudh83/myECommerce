package com.pilllr.errors.persistence;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 1/21/14
 * Time: 3:37 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GenericDAO<T, ID extends Serializable> {

    T findById(ID id);
    List<T> findAll();
    List<T> findByExample(T exampleInstance, String... excludeProperty);
    T makePersistent(T entity);
    void delete(T entity);
    void flush();
    void clear();

}