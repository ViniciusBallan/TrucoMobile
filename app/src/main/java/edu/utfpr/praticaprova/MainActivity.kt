package edu.utfpr.praticaprova

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputBinding
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.utfpr.praticaprova.databinding.ActivityMainBinding

// ViewBinding: build.gradle > android { buildFeatures { viewBinding = true } } + Sync
// Classe = nome do xml em PascalCase + Binding (activity_main -> ActivityMainBinding)
// binding = ActivityMainBinding.inflate(layoutInflater); setContentView(binding.root)
// Acesso: binding.idEmCamelCase | .text exige String -> usar .toString()
// Clique: binding.btn.setOnClickListener { ... }
// Atualizar tela: SEMPRE chamar atualizarTela() depois de mudar a variavel
// Vitoria: >= 12, mostrar Toast ANTES de zerar, zerar os DOIS times, vitorias++
// Toast: Toast.makeText(this, R.string.x, Toast.LENGTH_SHORT).show()
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var pontosNos = 0
    private var pontosEles = 0
    private var vitoriasNos = 0
    private var vitoriasEles = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        savedInstanceState?.let {
            pontosNos = it.getInt("pontosNos")
            pontosEles = it.getInt("pontosEles")
            vitoriasNos = it.getInt("vitoriasNos")
            vitoriasEles = it.getInt("vitoriasEles")
        }
        atualizarTela()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnNos1.setOnClickListener { marcarNos(1) }
        binding.btnNos3.setOnClickListener { marcarNos(3) }
        binding.btnNos6.setOnClickListener { marcarNos(6) }
        binding.btnNos9.setOnClickListener { marcarNos(9) }
        binding.btnNos12.setOnClickListener { marcarNos(12) }

        binding.btnEles1.setOnClickListener { marcarEles(1) }
        binding.btnEles3.setOnClickListener { marcarEles(3) }
        binding.btnEles6.setOnClickListener { marcarEles(6) }
        binding.btnEles9.setOnClickListener { marcarEles(9) }
        binding.btnEles12.setOnClickListener { marcarEles(12) }

        // Intent(this, HistoricoActivity::class.java) + putExtra("chave", valor) + startActivity(intent)
        // Tela nova no Manifest
        binding.btnHistorico.setOnClickListener {
            val intent = Intent(this, HistoricoActivity::class.java)
            intent.putExtra("vitoriasNos", vitoriasNos)
            intent.putExtra("vitoriasEles", vitoriasEles)
            startActivity(intent)
        }

        binding.btnZerarHistorico.setOnClickListener {
            vitoriasNos = 0
            vitoriasEles = 0
            pontosEles = 0
            pontosNos = 0
            atualizarTela()
            Toast.makeText(this, R.string.pontosZerados, Toast.LENGTH_SHORT).show()
        }

        binding.btnLimpar.setOnClickListener {
            pontosNos = 0
            pontosEles = 0
            atualizarTela()
            Toast.makeText(this, R.string.pontosZerados, Toast.LENGTH_SHORT).show()
        }
    }

    private fun atualizarTela(){
        binding.tvNosValor.text = pontosNos.toString()
        binding.tvElesValor.text = pontosEles.toString()
    }

    private fun marcarNos(valor: Int){
        pontosNos += valor
        if(pontosNos >= 12){
            vitoriasNos++
            anunciarVitoria(getString(R.string.n_s), pontosNos)

            pontosNos = 0
            pontosEles = 0
        }
        atualizarTela()
    }

    private fun marcarEles(valor: Int){
        pontosEles += valor
        if(pontosEles >= 12){
            vitoriasEles++
            AlertDialog.Builder(this)
                .setTitle("Fim de jogo")
                .setMessage("Eles ganharam a partida!")
                .setPositiveButton("OK", null)
                .show()
            pontosNos = 0
            pontosEles = 0
        }
        atualizarTela()
    }

    private fun anunciarVitoria(nome: String, pontos: Int) {
        Toast.makeText(this, getString(R.string.vitoria, nome, pontos), Toast.LENGTH_SHORT).show()
    }

    // Girar/trocar tema/idioma zera as variaveis
    // onSaveInstanceState(outState): super + outState.putInt("chave", valor)
    // onCreate apos setContentView: savedInstanceState?.let { valor = it.getInt("chave") } + atualizarTela()
    // Chaves IDENTICAS nos dois lados

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("pontosNos", pontosNos)
        outState.putInt("pontosEles", pontosEles)
        outState.putInt("vitoriasNos", vitoriasNos)
        outState.putInt("vitoriasEles", vitoriasEles)
    }
}