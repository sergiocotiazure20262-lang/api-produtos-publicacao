package br.com.cotiinformatica.api_produtos.dtos;

public record ProdutoRequestDto(
        String nome,
        Double preco,
        Integer quantidade
) {
}
