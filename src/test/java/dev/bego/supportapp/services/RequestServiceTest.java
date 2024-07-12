package dev.bego.supportapp.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dev.bego.supportapp.models.Request;
import dev.bego.supportapp.repositories.RequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RequestServiceTest {

    @InjectMocks
    RequestService service;

    @Mock
    RequestRepository repository;

    @BeforeEach
    void setUp() {
        this.service = new RequestService(repository);
    }

    @Test
    void testFindAll() {

        List<Request> requests = new ArrayList<>();
        Request request1 = new Request();
        request1.setId(1L);
        request1.setRequesterName("Miku Hatsune");
        request1.setTopic("Topic ex 1");
        request1.setDescription("Description ex 1");

        Request request2 = new Request();
        request2.setId(2L);
        request2.setRequesterName("Bego Blanco");
        request2.setTopic("Topic ex 2");
        request2.setDescription("Description ex 2");

        requests.add(request1);
        requests.add(request2);

        when(repository.findAll()).thenReturn(requests);
        List<Request> result = service.findAll();

        assertThat(result.size(), equalTo(2));
        assertThat(result.get(0).getRequesterName(), equalTo(request1.getRequesterName()));
        assertThat(result, contains(request1, request2));
    }

    @Test
    void testSave() {
        Request request = new Request();
        request.setId(1L);
        request.setRequesterName("Miku Hatsune");
        request.setTopic("Topic ex 1");
        request.setDescription("Description ex 1");

        when(repository.save(request)).thenReturn(request);
        Request result = service.save(request);

        assertThat(result.getId(), equalTo(request.getId()));
        assertThat(result.getRequesterName(), equalTo(request.getRequesterName()));
        assertThat(result.getTopic(), equalTo(request.getTopic()));
        assertThat(result.getDescription(), equalTo(request.getDescription()));
    }

    @Test
    void testUpdate() {
        Request existingRequest = new Request();
        existingRequest.setId(1L);
        existingRequest.setRequesterName("Miku Hatsune");
        existingRequest.setTopic("Old Topic");
        existingRequest.setDescription("Old Description");

        Request newRequest = new Request();
        newRequest.setRequesterName("Bego Blanco");
        newRequest.setTopic("New Topic");
        newRequest.setDescription("New Description");

        when(repository.findById(1L)).thenReturn(Optional.of(existingRequest));
        when(repository.save(existingRequest)).thenReturn(existingRequest);

        Request result = service.update(1L, newRequest);

        assertThat(result.getId(), equalTo(existingRequest.getId()));
        assertThat(result.getRequesterName(), equalTo(newRequest.getRequesterName()));
        assertThat(result.getTopic(), equalTo(newRequest.getTopic()));
        assertThat(result.getDescription(), equalTo(newRequest.getDescription()));
    }

    @Test
    void testDelete() {
        service.delete(1L);
    }
}
