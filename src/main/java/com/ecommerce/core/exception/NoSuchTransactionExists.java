package com.ecommerce.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 13/03/14
 * Time: 12:03 PM
 * To change this template use File | Settings | File Templates.
 */
@ResponseStatus(value = HttpStatus.NO_CONTENT, reason="No such Transaction Exists")
public class NoSuchTransactionExists extends RuntimeException{
    public NoSuchTransactionExists(String message) {
        super(message);
    }
}
