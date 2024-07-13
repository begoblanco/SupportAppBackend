package dev.bego.supportapp.controllers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dev.bego.supportapp.models.Request;
import dev.bego.supportapp.services.RequestService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = RequestController.class)
// @AutoConfigureMockMvc(addFilters = false) // Uncomment to disable security
public class RequestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RequestService service;

    @Autowired
    ObjectMapper mapper;

    @Test
    @DisplayName("Should return a list of support requests")
    void testGetAllRequests() throws Exception {

        List<Request> requests = new ArrayList<>();
        Request request1 = new Request();
        request1.setId(1L);
        request1.setRequesterName("Miku Hatsune");
        request1.setTopic("Topic ex 1");
        request1.setDescription("Description ex 1");
        request1.setRequestDate(LocalDateTime.now());

        Request request2 = new Request();
        request2.setId(2L);
        request2.setRequesterName("Bego Blanco");
        request2.setTopic("Topic ex 2");
        request2.setDescription("Description ex 2");
        request2.setRequestDate(LocalDateTime.now());

        requests.add(request1);
        requests.add(request2);

        when(service.getAll()).thenReturn(requests);
        
        MockHttpServletResponse response = mockMvc.perform(get("/api/support-requests/all")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        System.out.println(response.getContentAsString());

        assertThat(response.getStatus(), is(200));
        assertThat(response.getContentAsString(), containsString(request1.getRequesterName()));
        assertThat(response.getContentAsString(), containsString(request2.getRequesterName()));
        assertThat(response.getContentAsString(), equalTo(mapper.writeValueAsString(requests)));
    }
}
