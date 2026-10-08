package edu.utfpr.praticaprova

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

// Teste unitario: pasta test, roda sem emulador, so testa funcoes/regras (nao enxerga Activity)
// Padrao: dado um valor -> chamo a funcao -> assert do resultado
// assertEquals(esperado, real) | assertTrue(x) | assertFalse(x)
// Casos: abaixo do limite (11), no limite (12), acima (15)
// Funcao testada fica FORA da classe da Activity (fun solta)

class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}