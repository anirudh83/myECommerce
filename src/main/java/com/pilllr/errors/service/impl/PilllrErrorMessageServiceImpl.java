package com.pilllr.errors.service.impl;

import com.pilllr.errors.domain.PilllrErrorMessage;
import com.pilllr.errors.persistence.PilllrErrorMessagePersistence;
import com.pilllr.errors.service.PilllrErrorMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 1/29/14
 * Time: 12:18 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class PilllrErrorMessageServiceImpl implements PilllrErrorMessageService {

    @Autowired
    private PilllrErrorMessagePersistence pilllrErrorMessagePersistence;

    @Override
    public PilllrErrorMessage get(Long id) {
        return pilllrErrorMessagePersistence.findById(id);
    }

    @Override
    public PilllrErrorMessage create(PilllrErrorMessage pilllrErrorMessage) {
        return pilllrErrorMessagePersistence.makePersistent(pilllrErrorMessage);
    }

    @Override
    public void delete(Long id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PilllrErrorMessage update(PilllrErrorMessage pilllrErrorMessage) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Collection<PilllrErrorMessage> findAll() {
        return pilllrErrorMessagePersistence.findAll();
    }
}
