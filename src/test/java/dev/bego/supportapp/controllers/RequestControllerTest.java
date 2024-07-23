package dev.bego.supportapp.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dev.bego.supportapp.models.Request;
import dev.bego.supportapp.services.RequestService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@WebMvcTest(controllers = RequestController.class)
public class RequestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RequestService service;

    @Autowired
    ObjectMapper mapper;

    private ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    @Test
    @DisplayName("Should return a list of support requests")
    void testGetAllRequests() throws Exception {
        List<Request> requests = new ArrayList<>();
        Request request1 = new Request();
        request1.setId(1L);
        request1.setRequesterName("Miku Hatsune");
        request1.setTopic("Topic ex 1");
        request1.setDescription("Description ex 1");
        request1.setRequestDate(LocalDateTime.of(2024, 7, 22, 0, 0));

        Request request2 = new Request();
        request2.setId(2L);
        request2.setRequesterName("Bego Blanco");
        request2.setTopic("Topic ex 2");
        request2.setDescription("Description ex 2");
        request2.setRequestDate(LocalDateTime.of(2024, 7, 23, 0, 0));

        requests.add(request1);
        requests.add(request2);

        when(service.getAll()).thenReturn(requests);

        String expectedJson = createObjectMapper().writeValueAsString(requests);
        System.out.println("Expected JSON: " + expectedJson); 

        mockMvc.perform(get("/api/support-requests/all")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    @DisplayName("Should return a request by ID")
    void testGetRequestById() throws Exception {
        Request request = new Request();
        request.setId(1L);
        request.setRequesterName("Miku Hatsune");
        request.setTopic("Topic ex 1");
        request.setDescription("Description ex 1");
        request.setRequestDate(LocalDateTime.of(2024, 7, 22, 0, 0));

        when(service.findById(1L)).thenReturn(Optional.of(request));

        String expectedJson = createObjectMapper().writeValueAsString(request);
        System.out.println("Expected JSON: " + expectedJson); // Imprime el JSON esperado

        mockMvc.perform(get("/api/support-requests/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

   @Test
@DisplayName("Should create a new request")
void testCreateRequest() throws Exception {
    Request request = new Request();
    request.setId(1L);
    request.setRequesterName("Miku Hatsune");
    request.setTopic("Topic ex 1");
    request.setDescription("Description ex 1");
    request.setRequestDate(LocalDateTime.of(2024, 7, 22, 0, 0));

    when(service.store(any(Request.class))).thenReturn(request);

    String requestJson = createObjectMapper().writeValueAsString(request);
    // System.out.println("Request JSON: " + requestJson); 

    String responseJson = mockMvc.perform(post("/api/support-requests/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson))
            .andExpect(status().isCreated())
            .andReturn()
            .getResponse()
            .getContentAsString();

    JSONAssert.assertEquals(requestJson, responseJson, JSONCompareMode.LENIENT);
}

@Test
@DisplayName("Should update an existing request")
void testUpdateRequest() throws Exception {
    Request request = new Request();
    request.setId(1L);
    request.setRequesterName("Miku Hatsune");
    request.setTopic("Topic ex 1");
    request.setDescription("Description ex 1");
    request.setRequestDate(LocalDateTime.of(2024, 7, 22, 0, 0));

    // when(service.update(1L, request)).thenReturn(request);
    when(service.update(eq(1L), any(Request.class))).thenReturn(request);

    String requestJson = createObjectMapper().writeValueAsString(request);
    // System.out.println("Request JSON: " + requestJson); 

    String responseJson = mockMvc.perform(put("/api/support-requests/update/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

    JSONAssert.assertEquals(requestJson, responseJson, JSONCompareMode.LENIENT);
}
    

    @Test
    @DisplayName("Should delete a request")
    void testDeleteRequest() throws Exception {
        mockMvc.perform(delete("/api/support-requests/delete/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
