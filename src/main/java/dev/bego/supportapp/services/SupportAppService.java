package dev.bego.supportapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.bego.supportapp.models.SupportApp;
import dev.bego.supportapp.repositories.SupportAppRepository;

@Service
public class SupportAppService {
     @Autowired
    private SupportAppRepository repository;

    public List<SupportApp> findAll() {
        return repository.findAll();
    }

    public SupportApp save(SupportApp supportApp) {
        return repository.save(supportApp);
    }

    public SupportApp update(Long id, SupportApp supportApp) {
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
