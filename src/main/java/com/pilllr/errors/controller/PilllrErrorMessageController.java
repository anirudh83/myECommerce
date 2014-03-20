package com.pilllr.errors.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pilllr.errors.domain.PilllrErrorMessage;
import com.pilllr.errors.service.PilllrErrorMessageService;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 1/29/14
 * Time: 12:16 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@Transactional(value = "errorTransactionManager")
@RequestMapping("/errors")
public class PilllrErrorMessageController {

    @Autowired
    private PilllrErrorMessageService pilllrErrorMessageService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET,produces =MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public PilllrErrorMessage getPilllrErrorMessage(@PathVariable Long id) {
        return populatePilllrErrorMessage(pilllrErrorMessageService.get(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody public Map<String, PilllrErrorMessage> getAllMessages() {
     Collection<PilllrErrorMessage> messages = pilllrErrorMessageService.findAll();
        Map<String,PilllrErrorMessage> messageMap = new HashMap<String,PilllrErrorMessage>();
            for(PilllrErrorMessage message:messages) {
                messageMap.put(message.getLookupCode(),message);
                       }
                      return messageMap;
    }


    private PilllrErrorMessage populatePilllrErrorMessage(PilllrErrorMessage persistedMsg) {
        PilllrErrorMessage message =  new PilllrErrorMessage();
        message.setId(persistedMsg.getId());
        message.setDetails(persistedMsg.getDetails());
        message.setErrorCode(persistedMsg.getErrorCode());
        message.setUserMessage(persistedMsg.getUserMessage());
        return message;
    }

    @RequestMapping(method = RequestMethod.POST ,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PilllrErrorMessage createPilllrErrorMessage(@RequestBody PilllrErrorMessage pilllrErrorMessage) {
        return pilllrErrorMessageService.create(pilllrErrorMessage);
    }


}
