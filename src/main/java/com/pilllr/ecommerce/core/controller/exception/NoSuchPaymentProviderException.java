package com.pilllr.ecommerce.core.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 3/5/14
 * Time: 3:41 PM
 * To change this template use File | Settings | File Templates.
 */
@ResponseStatus(value = HttpStatus.NO_CONTENT, reason="No such Payment Provider")
public class NoSuchPaymentProviderException extends RuntimeException{
    public NoSuchPaymentProviderException(String msg) {
        super(msg);
    }
}
