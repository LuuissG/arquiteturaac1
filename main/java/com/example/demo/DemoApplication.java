package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.models.Categoria; 
import com.example.demo.models.Produto; 
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.repositories.ProdutoRepository;  

import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired 
    private ProdutoRepository produtoRepository;

    @Autowired 
    private CategoriaRepository categoriaRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args); 
    }

    @Override
    public void run(String... args) throws Exception {
        

        Categoria suplementos = new Categoria(null, "Suplementos");
        Categoria acessorio = new Categoria(null, "acessorio");
        categoriaRepository.save(suplementos); 
        categoriaRepository.save(acessorio);  

        produtoRepository.save(new Produto(null, "Pré-Treino", 130.00, suplementos));
        produtoRepository.save(new Produto(null, "Barra de Proteína", 15.00, suplementos));
        produtoRepository.save(new Produto(null, "Strap", 50.00, acessorio));

        System.out.println("============================================\nProdutos com preço maior que R$100,00:");
        List<Produto> produtosCaros = produtoRepository.selecionarPrecoMaiorque(100.00);
        produtosCaros.forEach(p -> System.out.println(p.getNome()));

        ///////==========================================================================================================/////// 

        System.out.println("============================================\nProdutos com preço menor ou igual a R$50,00:");
        List<Produto> produtosBaratos = produtoRepository.selecionarPrecomenorigual(50.00);
        produtosBaratos.forEach(p -> System.out.println(p.getNome()));

        ///////==========================================================================================================///////

        System.out.println("============================================\nProdutos que começam com 'Pré':");
        List<Produto> produtosComPré = produtoRepository.selecionarNomeespecifico("Pré");
        produtosComPré.forEach(p -> System.out.println(p.getNome()));

        ///////==========================================================================================================///////

        System.out.println("============================================\nItens que começam com 'Sup':");
        List<Categoria> categoriasComSup = categoriaRepository.findByNomeStartingWith("Sup");
        categoriasComSup.forEach(c -> System.out.println(c.getNome()));

        ///////==========================================================================================================///////
         
        System.out.println("============================================\nCategoria com Suplementos:");
        Categoria categoriaComProdutos = categoriaRepository.findCategoriaFetchProdutos(suplementos.getId());
        categoriaComProdutos.getProdutos().forEach(p -> System.out.println(p.getNome()));
    }
}
