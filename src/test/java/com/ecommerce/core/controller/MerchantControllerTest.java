package com.ecommerce.core.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.mec.persistence.domain.PaymentProvider;
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

import com.ecommerce.core.domain.Merchant;
import com.ecommerce.core.service.MerchantService;
import com.ecommerce.core.transformer.MerchantTransformer;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 2/3/14
 * Time: 11:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class MerchantControllerTest {

    private MockMvc mockMvc;


    @InjectMocks
    private MerchantController merchantController = new MerchantController();

    @Mock
    private MerchantService mockMerchantService;

    @Mock
    private MerchantTransformer mockMerchantTransformer;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(merchantController).build();
        when(mockMerchantTransformer.getMerchantDTO((Merchant)any())).thenReturn(getMockMerchantDTO(1l));
        when(mockMerchantTransformer.getLocalDomainObjectFromDTO((com.mec.persistence.domain.Merchant)any())).thenReturn(getMockMerchantWithPaymentProvider(1l));
        when(mockMerchantTransformer.getMerchantDTOList((List<Merchant>)any())).thenReturn(getMockMerchantDTOList());

    }


    @Test
    public void shouldGetMerchant() throws Exception {

        when(mockMerchantService.get(anyLong())).thenReturn(getMockMerchantWithPaymentProvider(1l));

                mockMvc.perform(get("/merchants/1").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                        .andExpect(content().contentType("application/json;charset=UTF-8"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id", is(1)))
                        .andExpect(jsonPath("$.name").value("Test"))
                        .andExpect(jsonPath("$.paymentProvider.name").value("WEBPAY"));
    }

    @Test
    public void shouldGetMerchantWhenNoPaymentProviderExists() throws Exception {

        when(mockMerchantService.get(anyLong())).thenReturn(getMockMerchant(1l));

        mockMvc.perform(get("/merchants/1").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name").value("Test"));

    }

    @Test
    public void shouldGetAllMerchants() throws Exception {
        when(mockMerchantService.findAll()).thenReturn(getMockMerchantList());

        mockMvc.perform(get("/merchants").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldPostMerchant() throws Exception {
        com.mec.persistence.domain.Merchant mockInputMerchant = getMockMerchantDTO(null);
        Merchant mockOutputMerchant = getMockMerchantWithPaymentProvider(null);;
        mockOutputMerchant.setId(1l);

        when(mockMerchantService.persist((Merchant)any())).thenReturn(mockOutputMerchant);

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(mockInputMerchant);

        this.mockMvc.perform(post("/merchants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s.getBytes()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

    }
    
    @Test
    public void shouldUpdateMerchant() throws Exception {
        com.mec.persistence.domain.Merchant mockInputMerchant = getMockMerchantDTO(1l);
        Merchant mockOutputMerchant = getMockMerchantWithPaymentProvider(1l);

        when(mockMerchantService.get(1l)).thenReturn(mockOutputMerchant);
        when(mockMerchantService.persist((Merchant)any())).thenReturn(mockOutputMerchant);

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(mockInputMerchant);

        this.mockMvc.perform(put("/merchants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s.getBytes()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

    }
    
    @Test
    public void shouldNotUpdateNonExistingMerchant() throws Exception {

        com.mec.persistence.domain.Merchant mockInputMerchant = getMockMerchantDTO(1l);

        ObjectNotFoundException e  = new ObjectNotFoundException(1,"No Such Merchant");
        when(mockMerchantService.get(1l)).thenThrow(e);

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(mockInputMerchant);

        this.mockMvc.perform(put("/merchants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s.getBytes()))
                .andExpect(status().isNoContent());

    }
    
    @Test
    public void shouldDeleteMerchant() throws Exception {
        Merchant mockInputMerchant = getMockMerchantWithPaymentProvider(null);
        Merchant mockOutputMerchant = mockInputMerchant;
        mockOutputMerchant.setId(1l);

        mockMerchantService.delete(1l);

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(mockInputMerchant);

        this.mockMvc.perform(delete("/merchants/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s.getBytes()))
                .andExpect(status().isOk())
                ;

    }

    private Merchant getMockMerchantWithPaymentProvider(Long id) {
        Merchant merchant = new Merchant();
        merchant.setId(id);
        merchant.setName("Test");
        com.ecommerce.core.domain.PaymentProvider paymentProvider = new com.ecommerce.core.domain.PaymentProvider();
        paymentProvider.setName("WEBPAY");
        merchant.setPaymentProvider(paymentProvider);
        return merchant;
    }

    private Merchant getMockMerchant(Long id) {
        Merchant merchant = new Merchant();
        merchant.setId(id);
        merchant.setName("Test");
        return merchant;
    }

    private com.mec.persistence.domain.Merchant getMockMerchantDTO(Long id){
        com.mec.persistence.domain.Merchant merchantDTO = new com.mec.persistence.domain.Merchant();
        merchantDTO.setId(id);
        merchantDTO.setName("Test");
        PaymentProvider paymentProvider = new PaymentProvider();
        paymentProvider.setName("WEBPAY");
        merchantDTO.setPaymentProvider(paymentProvider);
        return merchantDTO;
    }

    private List<Merchant> getMockMerchantList(){
        List<Merchant> merchantList  = new ArrayList<Merchant>();
        merchantList.add(getMockMerchantWithPaymentProvider(1l));
        merchantList.add(getMockMerchantWithPaymentProvider(2l));
        return merchantList;
    }

    private List<com.mec.persistence.domain.Merchant> getMockMerchantDTOList(){
        List<com.mec.persistence.domain.Merchant> merchantDTOList = new ArrayList<com.mec.persistence.domain.Merchant>();
        merchantDTOList.add(getMockMerchantDTO(1l));
        merchantDTOList.add(getMockMerchantDTO(2l));
        return merchantDTOList;
    }


}
