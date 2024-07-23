package dev.bego.supportapp.controllers;

import java.util.List;

// import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
// import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import dev.bego.supportapp.models.Request;
import dev.bego.supportapp.services.RequestService;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/api/support-requests")
public class RequestController {

    // @Autowired
    // private RequestService service;

    private RequestService service;

    public RequestController(RequestService service) { //usando constructor en vez de autowired :( )
        this.service = service;
    }

    @GetMapping("/all")
    public List<Request> getAllRequests() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable Long id) {
        Request request = service.findById(id).orElseThrow(() -> new RuntimeException("Request not found"));
        return ResponseEntity.ok(request);
    }

    @PostMapping("/add")
    public ResponseEntity<Request> createRequest(@RequestBody Request newRequest) {
        Request createdRequest = service.store(newRequest);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(createdRequest);
        // return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Request>updateRequest(@PathVariable Long id, @RequestBody Request updatedRequest) {
        Request updated = service.update(id, updatedRequest);
    return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
