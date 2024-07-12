package dev.bego.supportapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.bego.supportapp.models.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {
    
}
