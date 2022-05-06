package io.red;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class MockitoTests {

    @Test
    @DisplayName("Primeiro teste com Mockito")
    void primeiroTesteMockito(){
        // cenario
        List<String> lista = Mockito.mock(ArrayList.class);
        Mockito.when(lista.size()).thenReturn(20);
        // execucao
        int size = lista.size();
        // verificacao
        Assertions.assertThat(size).isEqualTo(20);
    }

}
