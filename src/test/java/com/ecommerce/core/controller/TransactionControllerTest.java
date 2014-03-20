package com.ecommerce.core.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ecommerce.core.exception.NoSuchTransactionExists;
import com.mec.persistence.domain.InitRequest;
import com.mec.persistence.domain.Merchant;
import com.mec.persistence.domain.TransactionStatus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ecommerce.core.domain.Transaction;
import com.ecommerce.core.service.TransactionService;

/**
 * 
 * @author anirudh
 *
 */
public class TransactionControllerTest {

    private MockMvc mockMvc;
    private static final String timestamp = new Timestamp(new Date().getTime()).toString();


    @InjectMocks
    private TransactionController transactionController = new TransactionController();

    @Mock
    private TransactionService mockTransactionService;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
    }

    @Test
    public void shouldGetAllTransactions() throws Exception {
        when(mockTransactionService.findAll()).thenReturn(getMockTransactionList());

        mockMvc.perform(get("/transactions").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldNotUpdateTransactionStatus() throws Exception {

       String STATUS_JSON = "{\"pilllrTransactionId\" : \"b7875cfa-9ece-11e3-9f64-17c0d3c499fe\" , \"status\": \"FAILED\" }";

        this.mockMvc.perform(post("/transactions/status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(STATUS_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("FAILED AS TRANSACTION DOES NOT EXIST"));

    }

    @Test
    public void shouldUpdateTransactionStatus() throws Exception {

        when(mockTransactionService.findByPilllrTransactionId("b7875cfa-9ece-11e3-9f64-17c0d3c499fe")).thenReturn(getMockTransaction(1l));
        when(mockTransactionService.persist((Transaction) any())).thenReturn(getMockTransaction(1l));

        String STATUS_JSON = "{\"pilllrTransactionId\" : \"b7875cfa-9ece-11e3-9f64-17c0d3c499fe\" , \"status\": \"FAILED\" }";

        this.mockMvc.perform(post("/transactions/status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(STATUS_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pilllrTransactionId").value("b7875cfa-9ece-11e3-9f64-17c0d3c499fe"))
                .andExpect(jsonPath("$.status").value("FAILED"));

    }

    @Test
    public void shouldCreateTransaction() throws Exception{
        String STATUS_JSON = "{\"pilllrTransactionId\" : \"b7875cfa-9ece-11e3-9f64-17c0d3c499fe\" , \"status\": \"FAILED\" }";

        this.mockMvc.perform(post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(STATUS_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pilllrTransactionId").value("b7875cfa-9ece-11e3-9f64-17c0d3c499fe"))
                .andExpect(jsonPath("$.status").value("PENDING"));
    }

    @Test
    public void shouldGetTransactionWithPilllrTxnIdIfExists() throws Exception {
        String pilllrTxnId = "b7875cfa-9ece-11e3-9f64-17c0d3c499fe";
        when(mockTransactionService.findByPilllrTransactionId(pilllrTxnId)).thenReturn(getMockTransaction(1l,pilllrTxnId));
        this.mockMvc.perform(get("/transactions/"+pilllrTxnId)
                     .contentType(MediaType.APPLICATION_JSON))
                     .andExpect(status().isOk())
                     .andExpect(jsonPath("$.pilllrTransactionId").value(pilllrTxnId))
                     .andExpect(jsonPath("$.paymentProvider").value("Web pay"))
                     .andExpect(jsonPath("$.createdDate").value(timestamp));
    }

    @Test
    public void shouldThrowErrorIfTxnDoesNotExist()throws Exception {
        String pilllrTxnId = "b7875cfa-9ece-11e3-9f64-17c0d3c499fe";
        NoSuchTransactionExists e = new NoSuchTransactionExists("No Txn");
        when(mockTransactionService.findByPilllrTransactionId(anyString())).thenThrow(e);
        this.mockMvc.perform(get("/transactions/"+pilllrTxnId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());


    }

    private Transaction getMockTransaction(Long id) {
        return getMockTransaction(id,"PILLLR_TRANSACTION_ID_"+ id);
    }

    private Transaction getMockTransaction(Long id,String PilllrTransactionId) {

        Transaction transaction = new Transaction();
        transaction.setId(id);
        transaction.setPaymentProvider("Web pay");
        transaction.setTransactionType("online");
        transaction.setPilllrTransactionId(PilllrTransactionId);
        transaction.setCreatedDate(timestamp);
        return transaction;
    }


    private List<Transaction> getMockTransactionList(){
        List<Transaction> transactionList  = new ArrayList<Transaction>();
        transactionList.add(getMockTransaction(1l));
        transactionList.add(getMockTransaction(2l));
        return transactionList;
    }
}
