package dev.bego.supportapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import dev.bego.supportapp.models.SupportApp;
import dev.bego.supportapp.services.SupportAppService;

@RestController
@RequestMapping("/api/support-requests")
public class SupportAppController {

       @Autowired
    private SupportAppService service;

    @GetMapping
    public List<SupportApp> getAllRequests() {
        return service.findAll();
    }

    @PostMapping
    public SupportApp createRequest(@RequestBody SupportApp supportApp) {
        return service.save(supportApp);
    }

    @PutMapping("/{id}")
    public SupportApp updateRequest(@PathVariable Long id, @RequestBody SupportApp supportApp) {
        return service.update(id, supportApp);
    }

    @DeleteMapping("/{id}")
    public void deleteRequest(@PathVariable Long id) {
        service.delete(id);
    }
    
}
