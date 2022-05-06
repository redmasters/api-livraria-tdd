package io.red;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CadastroPessoasTeste {

    @Test
    @DisplayName("Deve criar o cadastro de pessoas.")
    void deveCriarOCadastroDePessoas() {
        // cenario e execucao
        CadastroPessoas cadastro = new CadastroPessoas();
        // verificacao
        Assertions.assertThat(cadastro.getPessoas()).isEmpty();
    }

    @Test
    @DisplayName("Deve Adicionar uma pessoa valida.")
    void deveAdicionarUmaPessoa(){
        // cenario
        CadastroPessoas cadastro = new CadastroPessoas();
        Pessoa pessoa = new Pessoa("ReD");
        // execucao
        cadastro.adiciona(pessoa);
        // verificacao
        Assertions.assertThat(cadastro.getPessoas())
                .isNotEmpty()
                .hasSize(1)
                .contains(pessoa);
    }

    @Test
    @DisplayName("Nao deve cadastrar pessoa com nome vazio.")
    void naoDeveCadastrarPessoaComNomeVazio(){
        CadastroPessoas cadastro = new CadastroPessoas();
        Pessoa pessoa = new Pessoa();
        org.junit.jupiter.api.Assertions
                .assertThrows(PessoaSemNomeException.class, () -> cadastro.adiciona(pessoa));
    }

    @Test
    @DisplayName("Deve remover uma pessoa com sucesso.")
    void deveRemoverUmaPessoa(){
        CadastroPessoas cadastro = new CadastroPessoas();
        Pessoa pessoa = new Pessoa("ReD");
        cadastro.adiciona(pessoa);

        cadastro.remove(pessoa);
        Assertions.assertThat(cadastro.getPessoas()).isEmpty();
    }

    @Test
    @DisplayName("Deve lancar um erro ao tentar excluir pessoa inexistente.")
    void deveLancarErroAoTentarExcluirPessoaInexistente(){
        CadastroPessoas cadastro = new CadastroPessoas();
        Pessoa pessoa = new Pessoa();
        org.junit.jupiter.api.Assertions
                .assertThrows(CadastroVazioException.class,
                        () -> cadastro.remove(pessoa));
    }



}
