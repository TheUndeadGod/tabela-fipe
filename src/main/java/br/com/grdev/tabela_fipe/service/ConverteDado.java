package br.com.grdev.tabela_fipe.service;

import java.util.List;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDado implements IConverteDado {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDado(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> obterLista(String json, Class<T> classe) {
        try {
            JavaType listType = mapper.getTypeFactory().constructParametricType(List.class, classe);
            return mapper.readValue(json, listType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
