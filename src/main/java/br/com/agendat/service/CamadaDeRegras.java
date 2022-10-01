package br.com.agendat.service;

import br.com.agendat.dominio.Contato;
import br.com.agendat.repository.AgendaRepository;

import java.util.List;

public class CamadaDeRegras {

    public static void save(Contato contato){
        AgendaRepository.save(contato);
    }

    public static void deleteID(Integer id) {
        AgendaRepository.deleteById(id);
    }

    public static List<Contato> findByName(String name) {
        return AgendaRepository.findByName(name);
    }
}
