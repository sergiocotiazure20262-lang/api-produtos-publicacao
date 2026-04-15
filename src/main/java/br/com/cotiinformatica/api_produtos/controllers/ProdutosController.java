package br.com.cotiinformatica.api_produtos.controllers;

import br.com.cotiinformatica.api_produtos.dtos.ProdutoRequestDto;
import br.com.cotiinformatica.api_produtos.exceptions.ProdutoNaoEncontradoException;
import br.com.cotiinformatica.api_produtos.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/produtos")
public class ProdutosController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<?> post(@RequestBody ProdutoRequestDto request) {
        var response = produtoService.criar(request);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> put(@PathVariable Integer id, @RequestBody ProdutoRequestDto request) {
       try {
           var response = produtoService.atualizar(id, request);
           return ResponseEntity.status(200).body(response);
       }
       catch(ProdutoNaoEncontradoException e) {
            return ResponseEntity.status(404).body(e.getMessage());
       }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            var response = produtoService.excluir(id);
            return ResponseEntity.status(200).body(response);
        }
        catch(ProdutoNaoEncontradoException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        var response = produtoService.consultar();
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        try {
            var response = produtoService.obterPorId(id);
            return ResponseEntity.status(200).body(response);
        }
        catch(ProdutoNaoEncontradoException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
