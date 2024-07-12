package dev.bego.supportapp.controllers;

import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import dev.bego.supportapp.models.Request;
import dev.bego.supportapp.services.RequestService;

@RestController
@RequestMapping("/api/support-requests")
public class RequestController {

    // @Autowired
    // private RequestService service;

    private RequestService service;

    public RequestController(RequestService service) { //usando constructor en vez de autowired :( )
        this.service = service;
    }

    @GetMapping
    public List<Request> getAllRequests() {
        return service.findAll();
    }

    @PostMapping
    public Request createRequest(@RequestBody Request supportApp) {
        return service.save(supportApp);
    }

    @PutMapping("/{id}")
    public Request updateRequest(@PathVariable Long id, @RequestBody Request supportApp) {
        return service.update(id, supportApp);
    }

    @DeleteMapping("/{id}")
    public void deleteRequest(@PathVariable Long id) {
        service.delete(id);
    }
    
}
