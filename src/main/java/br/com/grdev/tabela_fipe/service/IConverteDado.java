package br.com.grdev.tabela_fipe.service;

import java.util.List;

public interface IConverteDado {

    <T> T obterDado(String json, Class<T> classe);

    <T> List<T> obterLista(String json, Class<T> classe);

}
