package br.com.agendat.service;

import br.com.agendat.dominio.Contato;
import br.com.agendat.repository.AgendaRepository;

import java.util.List;

public class CamadaDeRegras {

    public static void save(Contato contato) {
        AgendaRepository.save(contato);
    }

    public static void deleteID(Integer id) {
        requireValidId(id);
        AgendaRepository.deleteById(id);
    }

    public static List<Contato> findByName(String name) {
        return AgendaRepository.findByName(name);
    }

    public static void update(Contato contato) {
        requireValidId(contato.getId());
        AgendaRepository.update(contato);
    }

    private static void requireValidId(Integer id) {
        if (id == null && id <= 0) {
            throw new IllegalArgumentException("Valor inválido para id");
        }
    }
}
