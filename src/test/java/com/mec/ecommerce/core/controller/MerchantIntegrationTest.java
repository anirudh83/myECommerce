package com.mec.ecommerce.core.controller;

import com.mec.ecommerce.core.service.MerchantService;

import org.codehaus.jackson.map.ObjectMapper;
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
import org.springframework.web.util.NestedServletException;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 1/31/14
 * Time: 10:48 PM
 * To change this template use File | Settings | File Templates.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:applicationContext-test.xml"})
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MerchantIntegrationTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private MerchantService merchantService;


    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        populateMockMerchant();
    }

    private void populateMockMerchant() throws Exception {
        com.mec.persistence.domain.Merchant merchant = getMerchantDomainDTO("TEST");


        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(merchant);

        this.mockMvc.perform(post("/merchants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s.getBytes()))
                .andExpect(status().isOk());
    }



    @Test
    public void shouldGetMerchant() throws Exception {

        this.mockMvc.perform(get("/merchants/1").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("TEST"))
                .andExpect(jsonPath("$.paymentProvider.name").value("WEBPAY"));

    }


    @Test
    public void shouldCreateMerchant() throws Exception {

        com.mec.persistence.domain.Merchant merchant = getMerchantDomainDTO("TEST");

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(merchant);

        this.mockMvc.perform(post("/merchants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s.getBytes()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("TEST"));

    }
    
    @Test
    public void shouldUpdateMerchant() throws Exception {
    	
    	this.mockMvc.perform(get("/merchants/1").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
        .andExpect(content().contentType("application/json;charset=UTF-8"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.name").value("TEST"));

        com.mec.persistence.domain.Merchant merchant = getMerchantDomainDTO("TEST1");
        merchant.setId(1l);
        
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(merchant);

        this.mockMvc.perform(put("/merchants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s.getBytes()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("TEST1"));
    }
    
    @Test(expected=NestedServletException.class)
	public void shouldNotUpdateMerchant() throws Exception {

		this.mockMvc.perform(get("/merchants/1").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.name").value("TEST"));

		com.mec.persistence.domain.Merchant merchant = new com.mec.persistence.domain.Merchant();
		merchant.setName("TEST1");
		//merchant.setId(1l); Setting no id

		ObjectMapper mapper = new ObjectMapper();
		String s = mapper.writeValueAsString(merchant);

		this.mockMvc.perform(put("/merchants").contentType(MediaType.APPLICATION_JSON).content(s.getBytes())).andExpect(status().isInternalServerError());
	}
    
    
    @Test
    public void shouldDeleteMerchant() throws Exception {
        com.mec.persistence.domain.Merchant merchant = getMerchantDomainDTO("TEST1");

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(merchant);

        this.mockMvc.perform(post("/merchants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s.getBytes()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("TEST1"));
        
        this.mockMvc.perform(delete("/merchants/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s.getBytes()))
                .andExpect(status().isOk());
        
    }

    @Test
    public void shouldGetAllMerchants() throws Exception {

        mockMvc.perform(get("/merchants"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void shouldNotPersistMerchantIfNoPaymentProvider() throws Exception {
        com.mec.persistence.domain.Merchant merchant = new com.mec.persistence.domain.Merchant();
        merchant.setName("TEST");

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(merchant);

        this.mockMvc.perform(post("/merchants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s.getBytes()))
                .andExpect(status().isBadRequest());

    }

    private com.mec.persistence.domain.Merchant getMerchantDomainDTO(String name) {
        com.mec.persistence.domain.Merchant merchant = new com.mec.persistence.domain.Merchant();
        merchant.setName(name);
        com.mec.persistence.domain.PaymentProvider paymentProvider = new com.mec.persistence.domain.PaymentProvider();
        paymentProvider.setName("WEBPAY");
        paymentProvider.setClientId("test");
        merchant.setPaymentProvider(paymentProvider);
        return merchant;
    }


}
