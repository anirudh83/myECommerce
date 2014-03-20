package com.pilllr.ecommerce.core.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 3/4/14
 * Time: 4:44 PM
 * To change this template use File | Settings | File Templates.
 */

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="No Payment Provider provided")
public class NoPaymentProviderProvidedException extends RuntimeException {

    public NoPaymentProviderProvidedException(String message) {
        super(message);
    }
}