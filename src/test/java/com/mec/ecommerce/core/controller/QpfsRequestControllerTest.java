package com.mec.ecommerce.core.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.mec.ecommerce.core.service.MerchantService;
import com.mec.persistence.domain.*;
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

import com.mec.ecommerce.core.service.OrderService;
import com.mec.ecommerce.core.transformer.QpfsRequestTransformer;


/**
 * 
 * @author sunil
 *
 */
public class QpfsRequestControllerTest {

    private MockMvc mockMvc;


    @InjectMocks
    private QpfsRequestController qpfsRequestController = new QpfsRequestController();

    @Mock
    private OrderService mockOrderService;

    @Mock
    private QpfsRequestTransformer mockQpfsRequestTransformer;

    @Mock
    private MerchantService mockMerchantService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(qpfsRequestController).build();
        when(mockQpfsRequestTransformer.getOrder((QpfsRequest)any())).thenReturn(getMockOrder(null));

    }

    @Test
    public void shouldPostQPfsRequest() throws Exception {
        QpfsRequest mockInputQpfsRequest = getMockQpfsRequest(null);
        com.mec.ecommerce.core.domain.Order mockOutputOrder = getMockOrder(null);
        mockOutputOrder.setId(1l);

        when(mockMerchantService.get(anyLong())).thenReturn(getMockMerchant());
        when(mockOrderService.persist((com.mec.ecommerce.core.domain.Order)any())).thenReturn(mockOutputOrder);

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(mockInputQpfsRequest);

        this.mockMvc.perform(post("/qpfs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s.getBytes()))
                .andExpect(status().isOk());
    }

    @Test
    public void testShouldNotCreateTransactionIfMerchantIsNotValid() throws Exception {

        QpfsRequest qpfsRequest = new QpfsRequest();
        Merchant merchant = new Merchant();
        merchant.setId(1l);
        qpfsRequest.setMerchant(merchant);

        ObjectNotFoundException e = new ObjectNotFoundException(1,"No Merchant Exists with this id");
        when(mockMerchantService.get(anyLong())).thenThrow(e);

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(qpfsRequest);

        this.mockMvc.perform(post("/qpfs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s.getBytes()))
                .andExpect(status().isNoContent());
    }
    
    
    private QpfsRequest getMockQpfsRequest(Long id) {
       QpfsRequest qpfsRequest = new QpfsRequest();
       QpfsTransaction transaction = new QpfsTransaction();
        Merchant merchant = new Merchant();
        merchant.setId(1l);
        qpfsRequest.setMerchant(merchant);
       transaction.setProducts(getMockProducts());
       transaction.setOrder(getMockOrderDTO(1l));
       qpfsRequest.setTransaction(transaction);

       return qpfsRequest;
    }
    
    private List<Product> getMockProducts() {
    	ArrayList<Product> products = new ArrayList<Product>();
    	
    	Product product = new Product();
    	product.setDescription("Product1");
    	product.setPrice(Double.valueOf("30.40"));
    	product.setProductCategory("travel");
    	product.setProductCode("travel_code");
    	
    	products.add(product);
    	
    	return products;
	}


	private com.mec.ecommerce.core.domain.Order getMockOrder(Long id) {
        com.mec.ecommerce.core.domain.Order order = new com.mec.ecommerce.core.domain.Order();
        order.setId(id);
        order.setOrderType("Test");
        return order;
    }

    private Order getMockOrderDTO(Long id){
        Order orderDTO = new Order();
        orderDTO.setOrderId(String.valueOf(id));
        orderDTO.setOrderType("Test");
        return orderDTO;
    }

    private com.mec.ecommerce.core.domain.Merchant getMockMerchant() {
        com.mec.ecommerce.core.domain.Merchant merchant = new com.mec.ecommerce.core.domain.Merchant();
        return merchant;
    }
   
}
