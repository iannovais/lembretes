package com.iannovais.lembrete.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iannovais.lembrete.entity.Lembrete;
import com.iannovais.lembrete.repository.LembreteRepository;

@Service
public class LembreteService {
    private LembreteRepository lembreteRepository;


    public LembreteService(LembreteRepository lembreteRepository) {
        this.lembreteRepository = lembreteRepository;
    }

    public List<Lembrete> create(Lembrete lembrete) {
        lembreteRepository.save(lembrete);
        return list();
    }

    public List<Lembrete> list() {
        return lembreteRepository.findAll();
    }

    public List<Lembrete> delete(Long id) {
        lembreteRepository.deleteById(id);
        return list();
    }
}
