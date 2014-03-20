package com.pilllr.errors.service;

import com.pilllr.errors.domain.PilllrErrorMessage;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 1/29/14
 * Time: 12:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PilllrErrorMessageService {
    PilllrErrorMessage get(Long id);
    PilllrErrorMessage create(PilllrErrorMessage pilllrErrorMessage);
    void delete(Long id);
    PilllrErrorMessage update(PilllrErrorMessage pilllrErrorMessage);

    Collection<PilllrErrorMessage> findAll();
}
