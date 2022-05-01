package io.red;

import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class PrimeiroTeste {
    private static final Logger LOGGER = Logger.getLogger(PrimeiroTeste.class.getName());

    @Test
    public void deveSomarDoisNumeros() {
        // cenario
        Calculadora calculadora = new Calculadora();
        int numero1 = 10, numero2 = 10;
        // execucao
        int resultado = calculadora.somar(numero1, numero2);
        // verificacoes
        Assertions.assertThat(resultado).isEqualTo(20);

    }

    @Test(expected = RuntimeException.class)
    public void naoDeveSomarDoisNumerosNegativos() {
        // cenario
        Calculadora calculadora = new Calculadora();
        int numero1 = -0, numero2 = 10;
        // execucao
        calculadora.somar(numero1, numero2);

    }

    @Test
    public void deveSubtrairDoisNumeros() {
        Calculadora calculadora = new Calculadora();
        int numero1 = 10, numero2 = 10;
        int resultado = calculadora.subtrair(numero1, numero2);
        Assertions.assertThat(resultado).isEqualTo(0);
    }

    @Test(expected = RuntimeException.class)
    public void naoDeveSubtrairDoisNumerosIguaisAZeroOuNegativos() {
        Calculadora calculadora = new Calculadora();
        int numero1 = -10, numero2 = 10;
        calculadora.subtrair(numero1, numero2);
    }

    @Test
    public void deveDividirDoisNumerosPositivos() {
        Calculadora calculadora = new Calculadora();
        int numero1 = 10, numero2 = 10;
        int resultado = calculadora.dividir(numero1, numero2);
        Assertions.assertThat(resultado).isEqualTo(1);
    }

    @Test(expected = RuntimeException.class)
    public void naoDeveDividirDoisNumerosNegativosOuIguaisAzero() {
        Calculadora calculadora = new Calculadora();
        int numero1 = -10, numero2 = 10;
        calculadora.dividir(numero1, numero2);
    }

    @Test
    public void deveMultiplicarDoisNumerosPositivos() {
        Calculadora calculadora = new Calculadora();
        int numero1 = 10, numero2 = 10;
        int resultado = calculadora.multiplicar(numero1, numero2);
        Assertions.assertThat(resultado).isEqualTo(100);
    }

    @Test(expected = RuntimeException.class)
    public void naoDeveMultiplicarDoisNumerosNegativosOuIguaisAzero() {
        Calculadora calculadora = new Calculadora();
        int numero1 = -10, numero2 = 10;
       calculadora.multiplicar(numero1, numero2);
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