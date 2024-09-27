package com.estoque.p1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estoque.p1.service.ProdutoService;
import com.estoque.p1.model.Produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;


@RestController
@RequestMapping("/produtos")
public class Controller {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> getAll(@RequestParam(required = false) String nome){
        if (nome != null && !nome.isEmpty()){
            List<Produto> produtos = produtoService.findByNome(nome);
            if (produtos != null && produtos.size() > 0){
                return ResponseEntity.ok(produtos);
            }
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(produtoService.findAll());
        }
        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Produto> get(@PathVariable("id") Long id){
        Produto produto = produtoService.find(id);
        if (produto != null){
            return ResponseEntity.ok(produto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Produto> create(@RequestBody Produto produto){
        produtoService.create(produto);
        URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(produto.getId())
                            .toUri();
        return ResponseEntity.created(location).body(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Produto> delete(@PathVariable("id") Long id){
        if (produtoService.delete(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<Produto> update(@RequestBody Produto produto){
        if (produtoService.update(produto)){
            return ResponseEntity.ok(produto);
        }
        return ResponseEntity.notFound().build();
    }
    
}