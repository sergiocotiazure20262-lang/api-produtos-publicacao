package br.com.cotiinformatica.api_produtos.dtos;

public record ProdutoResponseDto(
        Integer id,
        String nome,
        Double preco,
        Integer quantidade,
        Double total
) {
}
