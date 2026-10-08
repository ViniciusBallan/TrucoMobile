package edu.utfpr.praticaprova

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.utfpr.praticaprova.databinding.ActivityHistoricoBinding

// intent.getIntExtra("chave", 0) | chave IDENTICA ao putExtra
// binding.tv.text = valor.toString()
// Nao precisa salvar ao girar: o Intent guarda os dados
class HistoricoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoricoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoricoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var vitoriasNos = intent.getIntExtra("vitoriasNos", 0)
        var vitoriasEles = intent.getIntExtra("vitoriasEles", 0)

        binding.tvNosHistValor.text = vitoriasNos.toString()
        binding.tvHistElesValor.text = vitoriasEles.toString()
    }
}