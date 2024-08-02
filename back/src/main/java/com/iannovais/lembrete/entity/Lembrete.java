package com.iannovais.lembrete.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "lembretes")
public class Lembrete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String nome;
    private LocalDate data;

    public Long getID() {
        return ID;
    }

    public void setID(Long iD) {
        ID = iD;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null) {
            this.nome = nome;
        } else {
            throw new IllegalArgumentException("É necessário um nome para a tarefa");
        }
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        if (data.isAfter(LocalDate.now())) {
            this.data = data;
        } else {
            throw new IllegalArgumentException("A data deve ser maior que a data atual.");
        }
    }
}
