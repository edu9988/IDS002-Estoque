package com.estoque.p1.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

import com.estoque.p1.model.Produto;

@Service
public class ProdutoService {
    private static List<Produto> produtos = new ArrayList<Produto>();

    public ProdutoService(){
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
}

/*package com.fatecrl.contacorrente.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fatecrl.contacorrente.model.Conta;

@Service
public class ContaService {
    private static List<Conta> contas = new ArrayList<Conta>();

    public ContaService(){
        contaFake();
    }

    private void contaFake(){
        Conta conta = new Conta();
        conta.setAgencia(1234);
        conta.setId(1L);
        conta.setNumero("1234");
        conta.setSaldo(1000.00);
        conta.setTitular("Diego Neri");
        contas.add(conta);
    }

    public List<Conta> findAll(){
        return contas;
    }

    public Conta find(Conta conta){
        return contas.stream()
                     .filter(c -> c.equals(conta))
                     .findFirst().orElse(null);                           
    }

    public Conta find(Long id){
        return find(new Conta(id));
    }

    public List<Conta> findByTitular(String titular){
        return contas.stream()
                     .filter(c -> c.getTitular().toLowerCase().indexOf(titular.toLowerCase()) > -1)
                     .toList();
    }

    public void create(Conta conta){
        Long newId = (long) (contas.size() + 1);
        conta.setId(newId);
        contas.add(conta);
    }

    public Boolean delete(Long id){
        Conta _conta = find(id);
        if (_conta != null){
            contas.remove(_conta);
            return true;
        }
        return false;
    }

    public Boolean update(Conta conta){
        Conta _conta = find(conta);
        if (_conta != null){
            if (conta.getAgencia() != null && conta.getAgencia() > 0)
                _conta.setAgencia(conta.getAgencia());

            if (!conta.getNumero().isBlank())
                _conta.setNumero(conta.getNumero());

            if (conta.getSaldo() != null && conta.getSaldo() > 0)
                _conta.setSaldo(conta.getSaldo());

            if (!conta.getTitular().isBlank())
                _conta.setTitular(conta.getTitular());
            
            return true;
        }
        return false;
    }
} */