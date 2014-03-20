package com.mec.ecommerce.core.persistence.impl;

import com.mec.ecommerce.core.domain.Merchant;
import com.mec.ecommerce.core.persistence.MerchantPersistence;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 1/21/14
 * Time: 4:04 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class MerchantPersistenceImpl extends GenericDAOImpl<Merchant, Long> implements MerchantPersistence {
}
