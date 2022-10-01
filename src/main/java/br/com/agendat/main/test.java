package br.com.agendat.main;

import br.com.agendat.dominio.Contato;
import br.com.agendat.repository.AgendaRepository;
import br.com.agendat.service.CamadaDeRegras;

import java.util.List;

public class test {
    public static void main(String[] args) {

        Contato save = Contato.builder().nome("Igor").email("Igor@gmail.com").build();
        CamadaDeRegras.save(save);

        Contato updateNomeEmail = Contato.builder().id(2).nome("quiteria").email("quiteria@gmail.com").build();
        CamadaDeRegras.update(updateNomeEmail);

        CamadaDeRegras.deleteID(7);

        List<Contato> contatos = AgendaRepository.findByName("Igor");
        System.out.printf("Os produtos encontraram %s %n", contatos);
    }
}
