package br.com.agendat.dominio;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Contato {
    String nome;
    String email;
    Integer id;

}
