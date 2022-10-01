package br.com.agendat.main;

import br.com.agendat.dominio.Contato;
import br.com.agendat.repository.AgendaRepository;
import br.com.agendat.service.CamadaDeRegras;

import java.util.List;

public class test {
    public static void main(String[] args) {

        Contato save = Contato.builder().nome("Igor").email("Igor@gmai.com").build();
        CamadaDeRegras.save(save);

        List<Contato> contatos = AgendaRepository.findByName("Igor");
        System.out.printf("Os produtos encontraram %s %n", contatos);

        CamadaDeRegras.deleteID(1);







    }
}
