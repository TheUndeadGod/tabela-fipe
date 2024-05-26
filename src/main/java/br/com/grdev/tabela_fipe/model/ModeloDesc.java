package br.com.grdev.tabela_fipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ModeloDesc(
    @JsonAlias("codigo") Integer codigo, 
    @JsonAlias("nome") String nome) {

}
