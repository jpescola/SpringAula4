package com.example.springloja.controller;

import com.example.springloja.entities.Produto;
import com.example.springloja.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin
public class ProdutoController {
    @Autowired
    private ProdutoService service;

    @GetMapping
    public List<Produto> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public String criar(@Valid @RequestBody Produto produto) {
        service.salvar(produto);
        return "Produto " + produto.getNome() + " criado com sucesso!";
    }

    @PutMapping("/{id}")
    public String atualizar(@PathVariable Long id, @Valid @RequestBody Produto produto){
        produto.setId(id);
        service.atualizar(produto);
        return "Produto atualizado com sucesso!";
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id){
        service.excluir(id);
        return "Produto deletado com sucesso!";
    }

    @GetMapping("/filtro-nome")
    public List<Produto> filtrarPorNome(@RequestParam String nome) {
        return service.buscarPorNome(nome);
    }

    @GetMapping("/filtro-preco")
    public List<Produto> filtrarPorPreco(@RequestParam(name="valor") Double preco) {
        return service.buscarPorPrecoMaximo(preco);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id){
        Produto obj = service.buscarPorId(id);

        if (obj != null){
            return ResponseEntity.ok().body(obj);
        } else{
            return ResponseEntity.notFound().build();
        }
    }
}
