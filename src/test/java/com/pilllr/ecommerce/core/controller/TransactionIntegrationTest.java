package com.pilllr.ecommerce.core.controller;

import com.pilllr.ecommerce.core.service.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 3/7/14
 * Time: 3:36 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TransactionIntegrationTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private TransactionService transactionService;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void shouldReturnUpdatedTransaction() throws Exception {
        String STATUS_JSON = "{\"pilllrTransactionId\" : \"b7875cfa-9ece-11e3-9f64-17c0d3c499fe\" , \"status\": \"FAILED\" }";

        this.mockMvc.perform(post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(STATUS_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pilllrTransactionId").value("b7875cfa-9ece-11e3-9f64-17c0d3c499fe"))
                .andExpect(jsonPath("$.status").value("PENDING"));

        this.mockMvc.perform(post("/transactions/status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(STATUS_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pilllrTransactionId").value("b7875cfa-9ece-11e3-9f64-17c0d3c499fe"))
                .andExpect(jsonPath("$.status").value("FAILED"));
    }

    @Test
    public void shouldNotUpdateTransaction() throws Exception {
        String STATUS_JSON = "{\"pilllrTransactionId\" : \"b7875cfa-9ece-11e3-9f64-17c0d3c499fe\" , \"status\": \"FAILED\" }";

        this.mockMvc.perform(post("/transactions/status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(STATUS_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("FAILED AS TRANSACTION DOES NOT EXIST"));

    }

    @Test
    public void shouldGetTransactionByPilllrTxnId() throws Exception {
        String STATUS_JSON = "{\"pilllrTransactionId\" : \"b7875cfa-9ece-11e3-9f64-17c0d3c499fe\" , \"status\": \"FAILED\" }";

        String pilllrTxnId = "b7875cfa-9ece-11e3-9f64-17c0d3c499fe";
        this.mockMvc.perform(post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(STATUS_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pilllrTransactionId").value(pilllrTxnId))
                .andExpect(jsonPath("$.status").value("PENDING"));

        this.mockMvc.perform(get("/transactions/" + pilllrTxnId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pilllrTransactionId").value(pilllrTxnId))
                .andExpect(jsonPath("$.status").value("PENDING"));
                //.andExpect(jsonPath("$.createdDate").value(11111l));

    }


}
