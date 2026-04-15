package br.com.cotiinformatica.api_produtos.services;

import br.com.cotiinformatica.api_produtos.dtos.ProdutoRequestDto;
import br.com.cotiinformatica.api_produtos.dtos.ProdutoResponseDto;
import br.com.cotiinformatica.api_produtos.entities.Produto;
import br.com.cotiinformatica.api_produtos.exceptions.ProdutoNaoEncontradoException;
import br.com.cotiinformatica.api_produtos.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoResponseDto criar(ProdutoRequestDto request) {

        var produto = new Produto();

        produto.setNome(request.nome());
        produto.setPreco(BigDecimal.valueOf(request.preco()));
        produto.setQuantidade(request.quantidade());

        produtoRepository.save(produto);

        return toResponse(produto);
    }

    public ProdutoResponseDto atualizar(Integer id, ProdutoRequestDto request) {

        var produto = produtoRepository.findById(id)
                        .orElseThrow(ProdutoNaoEncontradoException::new);

        produto.setNome(request.nome());
        produto.setPreco(BigDecimal.valueOf(request.preco()));
        produto.setQuantidade(request.quantidade());

        produtoRepository.save(produto);

        return toResponse(produto);
    }

    public ProdutoResponseDto excluir(Integer id) {

        var produto = produtoRepository.findById(id)
                .orElseThrow(ProdutoNaoEncontradoException::new);

        produtoRepository.delete(produto);

        return toResponse(produto);
    }

    public List<ProdutoResponseDto> consultar() {

        var produtos = produtoRepository.findAll();

        return produtos
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ProdutoResponseDto obterPorId(Integer id) {

        var produto = produtoRepository.findById(id)
                .orElseThrow(ProdutoNaoEncontradoException::new);

        return toResponse(produto);
    }

    private ProdutoResponseDto toResponse(Produto produto) {
        return new ProdutoResponseDto(
            produto.getId(),
            produto.getNome(),
            produto.getPreco().doubleValue(),
            produto.getQuantidade(),
            produto.getPreco().doubleValue() * produto.getQuantidade()
        );
    }
}
