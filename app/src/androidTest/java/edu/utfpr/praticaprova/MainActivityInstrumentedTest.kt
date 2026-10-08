package edu.utfpr.praticaprova

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

// Teste de instrumentacao: pasta androidTest, emulador ligado
// @RunWith(AndroidJUnit4::class) + @get:Rule ActivityScenarioRule(MainActivity::class.java)
// Agir:     onView(withId(R.id.btn)).perform(click())
// Conferir: onView(withId(R.id.tv)).check(matches(withText("9")))   <- texto sempre entre aspas
// Apos clicar no botao que troca de tela, ja posso usar os ids da tela nova
// Nao testar Toast. Imports: Alt+Enter (androidx.test... e org.junit...)
// Erro NoMatchingView = id errado ou tela errada | R vermelho = falta import do pacote

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun somaPontosDeNos() {
        onView(withId(R.id.btnNos3)).perform(click())
        onView(withId(R.id.btnNos6)).perform(click())
        onView(withId(R.id.tvNosValor)).check(matches(withText("9")))
    }

    @Test
    fun historico(){
        onView(withId(R.id.btnNos12)).perform(click())
        onView(withId(R.id.tvNosValor)).check(matches(withText("0")))
        onView(withId(R.id.btnHistorico)).perform(click())
        onView(withId(R.id.tvNosHistValor)).check(matches(withText("1")))
    }

    @Test
    fun btnLimpar(){
        onView(withId(R.id.btnEles6)).perform(click())
        onView(withId(R.id.btnLimpar)).perform(click())
        onView(withId(R.id.tvNosValor)).check(matches(withText("0")))
    }
}