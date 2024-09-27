package com.estoque.p1.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

import com.estoque.p1.model.Produto;

@Service
public class ProdutoService {
    private static List<Produto> produtos = new ArrayList<Produto>();

    public ProdutoService(){
        /* Adiciona produto para teste */
        Produto p = new Produto();
        p.setId(1L);
        p.setNome("caderno");
        p.setDescricao("50 folhas pautado");
        p.setQuantidade(7);
        p.setPreco(30.0);
        produtos.add(p);
    }

    public List<Produto> findAll(){
        return produtos;
    }

    public Produto find(Long id){
        return produtos.stream()
                     .filter(c -> c.getId() == id)
                     .findFirst().orElse(null);                           
    }

    public List<Produto> findByNome(String nome){
        return produtos.stream()
                     .filter(c -> c.getNome().toLowerCase().indexOf(nome.toLowerCase()) > -1)
                     .toList();
    }

    public void create(Produto produto){
        Long newId = (long) (produtos.size() + 1);
        produto.setId(newId);
        produtos.add(produto);
    }

    public Boolean delete(Long id){
        Produto _produto = find(id);
        if (_produto != null){
            produtos.remove(_produto);
            return true;
        }
        return false;
    }

    public Produto find(Produto produto){
        return produtos.stream()
                     .filter(c -> c.equals(produto))
                     .findFirst().orElse(null);                           
    }

    public Boolean update(Produto produto){
        Produto _produto = find(produto);
        if (_produto != null){
            if (produto.getQuantidade() != null && produto.getQuantidade() >= 0)
                _produto.setQuantidade(produto.getQuantidade());

            if (!produto.getNome().isBlank())
                _produto.setNome(produto.getNome());

            if (produto.getPreco() != null && produto.getPreco() > 0)
                _produto.setPreco(produto.getPreco());

            if (!produto.getDescricao().isBlank())
                _produto.setDescricao(produto.getDescricao());
            
            return true;
        }
        return false;
    }
}