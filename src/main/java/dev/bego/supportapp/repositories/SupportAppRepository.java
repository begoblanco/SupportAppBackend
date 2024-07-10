package dev.bego.supportapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.bego.supportapp.models.SupportApp;

public interface SupportAppRepository extends JpaRepository<SupportApp, Long> {
    
}
