package io.red;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PrimeiroTeste {

    @Mock
    Calculadora calculadora;
    int numero1 = 10, numero2 = 10;

    @BeforeEach
    public void setUp() {
        calculadora = new Calculadora();
    }

    @Test
    void deveSomarDoisNumeros() {
        // execucao
        int resultado = calculadora.somar(numero1, numero2);
        // verificacoes
        Assertions.assertThat(resultado).isEqualTo(20);

    }

    @Test
    void naoDeveSomarDoisNumerosNegativos() {

        // execucao
        int numero1 = -10, numero2 = 0;

        org.junit.jupiter.api.Assertions.
                assertThrows(RuntimeException.class, () -> calculadora.somar(numero1, numero2));

    }

    @Test
    void deveSubtrairDoisNumeros() {
        int resultado = calculadora.subtrair(numero1, numero2);
        Assertions.assertThat(resultado).isEqualTo(0);
    }

    @Test
    void naoDeveSubtrairDoisNumerosIguaisAZeroOuNegativos() {
        int numero1 = -10, numero2 = 0;
        org.junit.jupiter.api.Assertions
                .assertThrows(RuntimeException.class,
                        () -> calculadora.subtrair(numero1, numero2));
    }

    @Test
    void deveDividirDoisNumerosPositivos() {
        int resultado = calculadora.dividir(numero1, numero2);
        Assertions.assertThat(resultado).isEqualTo(1);
    }

    @Test
    @DisplayName("Nao deve dividir dois numeros Negativos ou Iguais a Zero.")
    void naoDeveDividirDoisNumerosNegativosOuIguaisAzero() {
        int numero1 = -10, numero2 = 10;
        org.junit.jupiter.api.Assertions
                .assertThrows(RuntimeException.class,
                        () -> calculadora.dividir(numero1, numero2));
    }

    @Test
    @DisplayName("Deve Multiplicar Dois numeros positivos.")
    void deveMultiplicarDoisNumerosPositivos() {
        Calculadora calculadora = new Calculadora();
        int numero1 = 10, numero2 = 10;
        int resultado = calculadora.multiplicar(numero1, numero2);
        Assertions.assertThat(resultado).isEqualTo(100);
    }

    @Test
    @DisplayName("Nao deve Multiplicar Numeros negativos ou Iguais a Zero.")
    void naoDeveMultiplicarDoisNumerosNegativosOuIguaisAzero() {
        Calculadora calculadora = new Calculadora();
        int numero1 = -10, numero2 = 10;
        org.junit.jupiter.api.Assertions
                .assertThrows(RuntimeException.class,
                        () -> calculadora.multiplicar(numero1, numero2));
    }

}

class Calculadora {
    // somar
    int somar(int numero1, int numero2) {
        if (numero1 <= 0 || numero2 <= 0)
            throw new RuntimeException("Nao deve se somar numeros iguais a zero o negativos");
        return numero1 + numero2;
    }

    // subtrair
    int subtrair(int numero1, int numero2) {
        if (numero1 <= 0 || numero2 <= 0)
            throw new RuntimeException("Nao se deve somar numeros negativos ou iguais a zero");
        return numero1 - numero2;

    }

    // dividir
    int dividir(int numero1, int numero2) {
        if (numero1 <= 0 || numero2 <= 0)
            throw new RuntimeException("Nao se deve dividir numeros negativos ou iguais a zero");
        return numero1 / numero2;
    }

    // multiplicar
    int multiplicar(int numero1, int numero2) {
        if (numero1 <= 0 || numero2 <= 0)
            throw new RuntimeException("Nao se deve multiplicar numeros negativos ou iguais a zero");
        return numero1 * numero2;
    }

}