package dev.bego.supportapp.services;

import dev.bego.supportapp.models.Request;
import dev.bego.supportapp.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RequestService {

    private final RequestRepository repository;

    @Autowired
    public RequestService(RequestRepository repository) {
        this.repository = repository;
    }

    public List<Request> getAll() {
        return repository.findAll();
    }

    public Request store(Request request) {
        request.setRequestDate(LocalDateTime.now());
        return repository.save(request);
    }

    public Request update(Long id, Request updatedRequest) {
        return repository.findById(id)
                .map(request -> {
                    request.setRequesterName(updatedRequest.getRequesterName());
                    request.setTopic(updatedRequest.getTopic());
                    request.setDescription(updatedRequest.getDescription());
                    return repository.save(request);
                })
                .orElseThrow(() -> new RuntimeException("Request not found with id " + id));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
