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


@RestController
@RequestMapping("/produtos")
public class Controller {

    @Autowired
    private ProdutoService produtoService;

    /*@GetMapping
    public ResponseEntity<List<Produto>> getAll(){
        return ResponseEntity.ok(produtoService.findAll());
    } */
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
}


/*package com.fatecrl.contacorrente.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatecrl.contacorrente.model.Conta;
import com.fatecrl.contacorrente.service.ContaService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/conta-corrente")
public class ContaCorrenteController {

    @Autowired
    private ContaService contaService;

    //Para executar: http://localhost:8090/api/conta-corrente
    @GetMapping
    public ResponseEntity<List<Conta>> getAll(@RequestParam(required = false) String titular){
        if (titular != null && !titular.isEmpty()){
            List<Conta> contas = contaService.findByTitular(titular);
            if (contas != null && contas.size() > 0){
                return ResponseEntity.ok(contas);
            }
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(contaService.findAll());
        }
        
    }
    
    //Para executar: http://localhost:8090/api/conta-corrente/1 (ok)
    //Para executar: http://localhost:8090/api/conta-corrente/999 (not found)
    @GetMapping("/{id}")
    public ResponseEntity<Conta> get(@PathVariable("id") Long id){
        Conta conta = contaService.find(id);
        if (conta != null){
            return ResponseEntity.ok(conta);
        }
        return ResponseEntity.notFound().build();
    }    

    @PostMapping
    public ResponseEntity<Conta> create(@RequestBody Conta conta){
        contaService.create(conta);
        URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(conta.getId())
                            .toUri();
        return ResponseEntity.created(location).body(conta);
    }

    @PutMapping
    public ResponseEntity<Conta> update(@RequestBody Conta conta){
        if (contaService.update(conta)){
            return ResponseEntity.ok(conta);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Conta> delete(@PathVariable("id") Long id){
        if (contaService.delete(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
} */