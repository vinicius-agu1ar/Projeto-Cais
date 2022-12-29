package br.com.compass.cais.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CodeErro {

    EMPRESA_NAO_ENCONTRADA("Empresa não encontrada, verifique antes de fazer esta solicitação novamente"),
    CAIS_NAO_ENCONTRADO("Cais não encontrado, verifique antes de fazer esta solicitação novamente"),
    NAVIO_NAO_ENCONTRADO("Navio não encontrado, verifique antes de fazer esta solicitação novamente"),
    ESTADIA_NAO_ENCONTRADA("Estadia não encontrada, verifique antes de fazer esta solicitação novamente"),
    PERFIL_NAO_ENCONTRADO("Perfil não encontrado, verifique antes de fazer esta solicitação novamente"),
    PEDIDO_RUIM("Requisição inválida"),
    INVALIDO_PARAMETRO("Parâmetro de solicitação inválido"),
    ERRO_INTERNO_SERVIDOR("Ocorreu um erro interno."),
    ENTIDADE_EM_USO("A entidade está em uso, verifique antes de executar esta ação novamente"),
    EMPRESA_EM_USO("A empresa não pode ser removida porque está em uso"),
    EMPRESA_JA_LIGADA("Uma empresa já está vinculada a este navio, desvincule primeiro"),
    CAIS_ESTA_EM_USO("O cais está em uso, verifique antes de executar esta ação novamente"),
    CAIS_CHEIO("O cais está cheio, tente mudar o cais"),
    NAVIO_NAO_COMPATIVEL("O Navio não possui vinculo com Empresa, Pier ou com nenhum dos dois"),
    NAVIO_ABERTO_EM_ESTADIA("Navio aberto em estadia, o navio não pode ter o status aberto em uma estadia");

    private final String menssagem;
}
