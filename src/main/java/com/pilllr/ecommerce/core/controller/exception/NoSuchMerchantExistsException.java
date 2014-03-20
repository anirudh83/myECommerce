package com.pilllr.ecommerce.core.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author anirudh
 *
 */
@ResponseStatus(value = HttpStatus.NO_CONTENT, reason="No such Merchant")
public class NoSuchMerchantExistsException extends RuntimeException {

	public NoSuchMerchantExistsException(String message) {
		super(message);
	}
	
}
