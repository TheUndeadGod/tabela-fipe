package br.com.grdev.tabela_fipe.principal;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import br.com.grdev.tabela_fipe.model.Marca;
import br.com.grdev.tabela_fipe.model.Modelo;
import br.com.grdev.tabela_fipe.model.ModeloAno;
import br.com.grdev.tabela_fipe.model.ModeloDesc;
import br.com.grdev.tabela_fipe.model.TabelaFipe;
import br.com.grdev.tabela_fipe.service.ConsumoApi;
import br.com.grdev.tabela_fipe.service.ConverteDado;

public class Principal {

    final private Scanner scanner = new Scanner(System.in);
    final private ConsumoApi consumoApi = new ConsumoApi();
    final private ConverteDado converteDado = new ConverteDado();

    final String MSG_ERRO_NUMERO_INVALIDO = "Valor invalido tente de novo depois.";
    final String URL_API = "https://parallelum.com.br/fipe/api/v1/";

    public void exibirMenu() {
        System.out.println("**** OPÇÕES ****");
        System.out.println("1-Carro\n2-Moto\n3-Caminhão");

        System.out.print("\nDigite uma das opções para consultar valores:");

        String opcaoPesquisa = "carros";
        try {
            int opcao = scanner.nextInt();
            scanner.nextLine();
            if (opcao < 1 || opcao > 3) {
                throw new Exception(MSG_ERRO_NUMERO_INVALIDO);
            }
            if (opcao == 2) {
                opcaoPesquisa = "motos";
            }
            else if (opcao == 3) {
                opcaoPesquisa = "caminhoes";
            }
        } catch (Exception e) {
            System.out.println(MSG_ERRO_NUMERO_INVALIDO);
            return;
        }

        String endereco = URL_API+opcaoPesquisa+"/marcas";
        var json = consumoApi.obterDados(endereco);

        List<Marca> marcas = converteDado.obterLista(json, Marca.class);
        
        marcas.stream()
            .sorted(Comparator.comparing(Marca::codigo))
            .forEach(e -> System.out.println("Cód: "+e.codigo() + " Descrição:"+e.nome()) );

        System.out.println("Informe o código da marca para consulta:");
        String codMarca = scanner.nextLine();

        endereco += "/"+codMarca+"/modelos";
        json = consumoApi.obterDados(endereco);

        Modelo modelos = converteDado.obterDado(json, Modelo.class);
   
        Arrays.asList(modelos.modelos()).stream()
        .sorted(Comparator.comparing(ModeloDesc::codigo))
        .forEach(e -> System.out.println("Cód: "+e.codigo() + " Descrição:"+e.nome()) );

        System.out.println("Digite um trecho do nome do veículo para consulta:");
        String trechoNomeVeiculo = scanner.nextLine();
        
        Arrays.asList(modelos.modelos()).stream()
            .filter(e -> e.nome().toUpperCase().contains(trechoNomeVeiculo.toUpperCase()) )
            .sorted(Comparator.comparing(ModeloDesc::codigo))
            .forEach(e -> System.out.println("Cód: "+e.codigo() + " Descrição:"+e.nome()) );

        System.out.println("Digite o código do modelo para consultar valores:");
        String codMoledo = scanner.nextLine();

        endereco += "/"+codMoledo+"/anos";
        json = consumoApi.obterDados(endereco);

        List<ModeloAno> modelosAnos = converteDado.obterLista(json, ModeloAno.class);

        final String url = endereco;
        modelosAnos.stream()
            .forEach(e -> {
                String jsonFipe = consumoApi.obterDados(url+"/"+e.codigo());
                TabelaFipe tabelaFipe = converteDado.obterDado(jsonFipe, TabelaFipe.class);
                System.out.println(tabelaFipe);
            });

        scanner.close();
    }

}
