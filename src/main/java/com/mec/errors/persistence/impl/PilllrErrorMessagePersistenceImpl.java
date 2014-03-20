package com.mec.errors.persistence.impl;

import com.mec.errors.domain.PilllrErrorMessage;
import com.mec.errors.persistence.PilllrErrorMessagePersistence;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 1/29/14
 * Time: 12:13 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class PilllrErrorMessagePersistenceImpl extends GenericDAOImpl<PilllrErrorMessage,Long> implements PilllrErrorMessagePersistence {
}
