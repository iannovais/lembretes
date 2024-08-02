package com.iannovais.lembrete.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iannovais.lembrete.entity.Lembrete;
import com.iannovais.lembrete.service.LembreteService;

@RestController
@RequestMapping("/lembretes")
public class LembreteController {
    private LembreteService lembreteService;

    public LembreteController(LembreteService lembreteService) {
        this.lembreteService = lembreteService;
    }

    @PostMapping
    List<Lembrete> create(@RequestBody Lembrete lembrete) {
        return lembreteService.criar(lembrete);
    }

    @GetMapping
    List<Lembrete> list() {
        return lembreteService.listar();
    }

    @GetMapping("/agrupados")
    public Map<String, List<Lembrete>> listGroupedByDate() {
        return lembreteService.listarPorGrupo();
    }

    @DeleteMapping("{id}")
    List<Lembrete> delete(@PathVariable Long id) {
        return lembreteService.deletar(id);
    }
}
