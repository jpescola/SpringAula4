package com.example.springloja.services;

import com.example.springloja.entities.Produto;
import com.example.springloja.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    public void salvar(Produto produto) {
        repository.save(produto);
    }

    public void atualizar(Produto produto){
        repository.save(produto);
    }

    public void excluir(Long id){
        repository.deleteById(id);
    }

    public List<Produto> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Produto> buscarPorPrecoMaximo(Double preco) {
        return repository.findByPrecoLessThanEqual(preco);
    }

    public Produto buscarPorId(Long id){
        return repository.findById(id).orElse(null);
    }
}
