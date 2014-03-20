package com.ecommerce.core.controller;

import com.ecommerce.core.domain.Transaction;
import com.ecommerce.core.service.MerchantService;
import com.ecommerce.core.service.TransactionService;
import com.ecommerce.core.transformer.MerchantTransformer;
import com.mec.persistence.domain.PaymentProvider;
import com.mec.persistence.domain.QpfsRequest;
import com.mec.persistence.domain.SettlementRequest;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.ObjectNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 3/3/14
 * Time: 6:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class SettlementRequestControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private SettlementRequestController settlementRequestController = new SettlementRequestController();

    @Mock
    private TransactionService mockTransactionService;

    @Mock
    private MerchantTransformer mockMerchantTransformer;

    @Mock
    private MerchantService mockMerchantService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(settlementRequestController).build();

    }

    @Test
    public void shouldNotCreateTransactionIfMerchantIsNotValid() throws Exception {

        SettlementRequest settlementRequest = getSettlementRequest();
        ObjectNotFoundException e = new ObjectNotFoundException(1, "No Merchant Exists with this id");
        when(mockMerchantService.get(anyLong())).thenThrow(e);

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(settlementRequest);

        this.mockMvc.perform(post("/settlement")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s.getBytes()))
                .andExpect(status().isNoContent());
    }


    @Test
    public void shouldCreateTransactionFromRequest() throws Exception {
        SettlementRequest settlementRequest = getSettlementRequest();

        when(mockMerchantService.get(anyLong())).thenReturn(getMockMerchant());
        when(mockTransactionService.persist((Transaction) any())).thenReturn(getMockTransaction(1l));

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(settlementRequest);

        this.mockMvc.perform(post("/settlement")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s.getBytes()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionType").value("SETTLEMENT"))
                .andExpect(jsonPath("$.paymentProvider").value("WEBPAY2"));
    }

    @Test
    public void shouldCreateTransactionWithDefaultProviderWhenNoProvider() throws Exception {

        when(mockMerchantService.get(anyLong())).thenReturn(getMockMerchant());
        when(mockTransactionService.persist((Transaction) any())).thenReturn(getMockTransaction(1l));

        SettlementRequest settlementRequest = new SettlementRequest();
        com.mec.persistence.domain.Merchant merchant = new com.mec.persistence.domain.Merchant();
        merchant.setId(1l);
        settlementRequest.setMerchant(merchant);

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(settlementRequest);

        this.mockMvc.perform(post("/settlement")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s.getBytes()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionType").value("SETTLEMENT"))
                .andExpect(jsonPath("$.paymentProvider").value("WEBPAY"));
    }


    private SettlementRequest getSettlementRequest() {
        SettlementRequest settlementRequest = new SettlementRequest();
        com.mec.persistence.domain.Merchant merchant = new com.mec.persistence.domain.Merchant();
        merchant.setId(1l);
        PaymentProvider paymentProvider = new PaymentProvider();
        paymentProvider.setName("WEBPAY2");
        merchant.setPaymentProvider(paymentProvider);
        settlementRequest.setMerchant(merchant);
        settlementRequest.setPilllrTransactionId("abcd-1234");
        return settlementRequest;
    }


    @Test
    public void shouldNotAcceptWrongJson() throws Exception {
        String JSON = "{\"pilllrTransactionId\":\"hell\",\"merchant\":{\"id\":1,\"name\":null,\"paymentProvider\":null},\"paymentProvider\":\"Webpay\"}";
        this.mockMvc.perform(post("/settlement")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON))
                .andExpect(status().isBadRequest());
    }


    private Transaction getMockTransaction(Long id) {
        Transaction transaction = new Transaction();
        transaction.setId(id);
        return transaction;
    }

    private com.ecommerce.core.domain.Merchant getMockMerchant() {
        com.ecommerce.core.domain.Merchant merchant = new com.ecommerce.core.domain.Merchant();
        return merchant;
    }
}
