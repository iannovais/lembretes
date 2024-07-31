package com.iannovais.lembrete.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iannovais.lembrete.entity.Lembrete;

public interface LembreteRepository extends JpaRepository<Lembrete, Long> {
    
}
