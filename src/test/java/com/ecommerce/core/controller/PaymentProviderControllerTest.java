package com.ecommerce.core.controller;

import com.ecommerce.core.domain.PaymentProvider;
import com.ecommerce.core.service.PaymentProviderService;
import com.ecommerce.core.transformer.PaymentProviderTransformer;
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
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author sunil
 */
public class PaymentProviderControllerTest {

    private MockMvc mockMvc;


    @InjectMocks
    private PaymentProviderController paymentProviderController = new PaymentProviderController();

    @Mock
    private PaymentProviderService mockPaymentProviderService;

    @Mock
    private PaymentProviderTransformer mockPaymentProviderTransformer;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(paymentProviderController).build();
        when(mockPaymentProviderTransformer.getPaymentProviderDTO((PaymentProvider) any())).thenReturn(getMockPaymentProviderDTO("test"));

    }


    @Test
    public void shouldFindPaymentProviderByName() throws Exception {
        when(mockPaymentProviderService.findByName(anyString())).thenReturn(getMockPaymentProvider("test"));
        mockMvc.perform(get("/paymentprovider/name/test").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.password").value("test"));
    }

    @Test
    public void shouldThrowExceptionWhenNoPaymentProvider() throws Exception {
        ObjectNotFoundException e = new ObjectNotFoundException("Test","Could not find PaymentProvider with name ");
        when(mockPaymentProviderService.findByName(anyString())).thenThrow(e);
        mockMvc.perform(get("/paymentprovider/name/test").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isNoContent());

    }


    private PaymentProvider getMockPaymentProvider(String name) {
        PaymentProvider paymentProvider = new PaymentProvider();
        paymentProvider.setId(1l);
        paymentProvider.setName(name);
        paymentProvider.setPassword("test");
        return paymentProvider;
    }

    private com.ecommerce.core.dto.PaymentProvider getMockPaymentProviderDTO(String name) {
        com.ecommerce.core.dto.PaymentProvider paymentProviderDTO = new com.ecommerce.core.dto.PaymentProvider();
        paymentProviderDTO.setId(1l);
        paymentProviderDTO.setName(name);
        paymentProviderDTO.setPassword("test");
        return paymentProviderDTO;
    }

}
