package br.com.grdev.tabela_fipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Modelo(
    @JsonAlias("anos") ModeloAno[] anos, 
    @JsonAlias("modelos") ModeloDesc[] modelos) {

}
