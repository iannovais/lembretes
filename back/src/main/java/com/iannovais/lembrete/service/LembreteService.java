package com.iannovais.lembrete.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.iannovais.lembrete.entity.Lembrete;
import com.iannovais.lembrete.repository.LembreteRepository;

@Service
public class LembreteService {
    private final LembreteRepository lembreteRepository;

    public LembreteService(LembreteRepository lembreteRepository) {
        this.lembreteRepository = lembreteRepository;
    }

    public List<Lembrete> criar(Lembrete lembrete) {
        lembreteRepository.save(lembrete);
        return listar();
    }

    public List<Lembrete> listar() {
        return lembreteRepository.findAll();
    }

    public List<Lembrete> deletar(Long id) {
        lembreteRepository.deleteById(id);
        return listar();
    }

    public Map<String, List<Lembrete>> listarPorGrupo() {
        List<Lembrete> lembretes = listar();

        List<Lembrete> lembretesOrdenados = lembretes.stream()
            .sorted((l1, l2) -> l1.getData().compareTo(l2.getData()))
            .collect(Collectors.toList());

        Map<String, List<Lembrete>> agrupadosPorData = lembretesOrdenados.stream()
            .collect(Collectors.groupingBy(
                lembrete -> lembrete.getData().toString(), 
                LinkedHashMap::new,
                Collectors.toList()
            ));
        
        return agrupadosPorData;
    }
}
