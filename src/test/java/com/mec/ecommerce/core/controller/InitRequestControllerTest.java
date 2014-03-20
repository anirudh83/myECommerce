package com.mec.ecommerce.core.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.mec.ecommerce.core.domain.Transaction;
import com.mec.ecommerce.core.service.MerchantService;
import com.mec.ecommerce.core.service.TransactionService;
import com.mec.ecommerce.core.transformer.MerchantTransformer;
import com.mec.persistence.domain.InitRequest;
import com.mec.persistence.domain.Merchant;

/**
 * 
 * @author sunil
 *
 */
public class InitRequestControllerTest {

    private MockMvc mockMvc;
    private String initUrl = "/init-hcs";
    private static final String JSON = "{\"pilllrTransactionId\":\"hell\",\"merchantId\":\"1\",\"paymentProvider\":\"Webpay\"}";


    @InjectMocks
    private InitRequestController initRequestController = new InitRequestController();

    @Mock
    private TransactionService mockTransactionService;

    @Mock
    private MerchantTransformer mockMerchantTransformer;
    
    @Mock
    private MerchantService mockMerchantService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(initRequestController).build();
        
    }

    @Test
    public void shouldNotCreateTransactionIfMerchantIsNotValid() throws Exception {

        InitRequest initRequest = getInitRequest();
        ObjectNotFoundException e = new ObjectNotFoundException(1,"No Merchant Exists with this id");
    	 when(mockMerchantService.get(anyLong())).thenThrow(e);
    	 
    	 ObjectMapper mapper = new ObjectMapper();
         String s = mapper.writeValueAsString(initRequest);

         this.mockMvc.perform(post(initUrl)
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(s.getBytes()))
                 .andExpect(status().isNoContent());
    }
    
    
    @Test
    public void shouldCreateTransactionFromInitRequest() throws Exception {
        InitRequest initRequest = getInitRequest();
    	
    	 when(mockMerchantService.get(anyLong())).thenReturn(getMockMerchant());
    	 when(mockTransactionService.persist((Transaction) any())).thenReturn(getMockTransaction(1l));
    	 
    	 ObjectMapper mapper = new ObjectMapper();
         String s = mapper.writeValueAsString(initRequest);


        this.mockMvc.perform(post(initUrl)
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(s.getBytes()))
                 .andExpect(status().isOk())
                 .andExpect(content().string(s));
    }

    private InitRequest getInitRequest() {
        InitRequest initRequest = new InitRequest();
        initRequest.setMerchantId("1");
        return initRequest;
    }

    @Test
    public void shouldNotAcceptWrongJson() throws Exception {
        String JSON = "{\"pilllrTransactionId\":\"hell\",\"merchant\":{\"id\":1,\"name\":null,\"paymentProvider\":null},\"paymentProvider\":\"Webpay\"}";
        this.mockMvc.perform(post(initUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON) )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldAcceptCorrectJson() throws Exception {

        this.mockMvc.perform(post(initUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON) )
                .andExpect(status().isOk())
                .andExpect(content().string(JSON));
    }

    private Transaction getMockTransaction(Long id) {
        Transaction transaction = new Transaction();
        transaction.setId(id);
        return transaction;
    }

    private com.mec.ecommerce.core.domain.Merchant getMockMerchant() {
		com.mec.ecommerce.core.domain.Merchant merchant = new com.mec.ecommerce.core.domain.Merchant();
		return merchant;
	}
}
