package com.example.demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.controller.entities.ClientEntity; 
  
public interface ClientRepository extends JpaRepository<ClientEntity, Integer> { 
	Page<ClientEntity> findByOrderByCreationDateAsc(Pageable pageable);
}
