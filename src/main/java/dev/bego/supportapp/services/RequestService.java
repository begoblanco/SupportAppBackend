package dev.bego.supportapp.services;

import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.bego.supportapp.models.Request;
import dev.bego.supportapp.repositories.RequestRepository;

@Service
public class RequestService {
    //  @Autowired
    // private RequestRepository repository;

    private final RequestRepository repository;

    public RequestService(RequestRepository repository) {
        this.repository = repository;
    }

    public List<Request> findAll() {
        return repository.findAll();
    }

    public Request save(Request supportApp) {
        return repository.save(supportApp);
    }

    public Request update(Long id, Request supportApp) {
        return repository.findById(id).map(existingSupportApp -> {
            existingSupportApp.setRequesterName(supportApp.getRequesterName());
            existingSupportApp.setTopic(supportApp.getTopic());
            existingSupportApp.setDescription(supportApp.getDescription());
            return repository.save(existingSupportApp);
        }).orElseThrow(() -> new RuntimeException("Support request not found with id " + id));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    
}
