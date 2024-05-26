package br.com.grdev.tabela_fipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TabelaFipe(
    @JsonAlias("Valor") String valor,
    @JsonAlias("Marca") String marca,
    @JsonAlias("Modelo") String modelo,
    @JsonAlias("AnoModelo") Integer ano,
    @JsonAlias("Combustivel") String combustivel) {

        public String toString() {
            return "Veiculo [valor="+valor+", marca="+marca+", modelo="+modelo+", ano="+ano+", combustivel="+combustivel+"]";
        }

}
