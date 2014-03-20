package com.mec.errors;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mec.errors.controller.PilllrErrorMessageController;
import com.mec.errors.domain.PilllrErrorMessage;
import com.mec.errors.service.PilllrErrorMessageService;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 2/3/14
 * Time: 10:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class PilllrErrorMessageControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private PilllrErrorMessageController pilllrErrorMessageController = new PilllrErrorMessageController();

    @Mock
    private PilllrErrorMessageService pilllrErrorMessageService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pilllrErrorMessageController).build();

    }

    @Test
    public void shouldGetPilllrErrorMessage() throws Exception {

        when(pilllrErrorMessageService.get(anyLong())).thenReturn(getMockPillrErrorMessage(1l));

                mockMvc.perform(get("/errors/1").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                        .andExpect(content().contentType("application/json;charset=UTF-8"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void shouldGetAllPilllrErrorMsgs() throws Exception {
        when(pilllrErrorMessageService.findAll()).thenReturn(getMockPilllrMessageList());

        mockMvc.perform(get("/errors").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk());
               // .andExpect(jsonPath("$", hasSize(2)));
    }
    
    @Test
    public void shouldPostPilllrErrorMsg() throws Exception {
    	PilllrErrorMessage mockPillrErrorMsg = getMockPillrErrorMessage(null);
    	PilllrErrorMessage mockOutputErrorMsg = mockPillrErrorMsg;
    	mockOutputErrorMsg.setId(1l);

        when(pilllrErrorMessageService.create((PilllrErrorMessage)any())).thenReturn(mockOutputErrorMsg);

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(mockPillrErrorMsg);

        this.mockMvc.perform(post("/errors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s.getBytes()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

    }
    
    
    
    
    private PilllrErrorMessage getMockPillrErrorMessage(Long id) {
    	PilllrErrorMessage pilllrErrorMessage = new PilllrErrorMessage();
    	pilllrErrorMessage.setId(id);
    	pilllrErrorMessage.setLookupCode("LookupCode-"+ id);
        return pilllrErrorMessage;
    }
    
    private List<PilllrErrorMessage> getMockPilllrMessageList(){
        List<PilllrErrorMessage> pilllrErrorMsgList  = new ArrayList<PilllrErrorMessage>();
        pilllrErrorMsgList.add(getMockPillrErrorMessage(1l));
        pilllrErrorMsgList.add(getMockPillrErrorMessage(2l));
        return pilllrErrorMsgList;
    }

}
